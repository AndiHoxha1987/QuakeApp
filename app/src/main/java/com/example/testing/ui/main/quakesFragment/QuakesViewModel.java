package com.example.testing.ui.main.quakesFragment;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.testing.R;
import com.example.testing.model.Features;
import com.example.testing.model.Properties;
import com.example.testing.model.Quake;
import com.example.testing.network.main.MainApi;
import com.example.testing.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class QuakesViewModel extends ViewModel {

    private static final String TAG = "QuakesViewModel";

    private final MainApi mainApi;
    private Application application;

    private MediatorLiveData<Resource<Features>> quakes;


    @Inject
    public QuakesViewModel(MainApi mainApi, Application application) {
        this.mainApi = mainApi;
        this.application = application;
        Log.d(TAG, "QuakesViewModel: ");
    }

    public LiveData<Resource<Features>> observeQuakes(){
        if(quakes == null){
            quakes = new MediatorLiveData<>();
            quakes.setValue(Resource.loading((Features) null));

            SharedPreferences preferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(application);
            String items = preferences.getString(application.getResources().getString(R.string.items_key),application.getResources().getString(R.string.settings_max_items_default));
            String minMagnitude = preferences.getString(application.getResources().getString(R.string.min_magnitude_key),application.getResources().getString(R.string.settings_min_magnitude_default));
            String maxMagnitude = preferences.getString(application.getResources().getString(R.string.max_magnitude_key),application.getResources().getString(R.string.settings_max_magnitude_default));

            final LiveData<Resource<Features>> source = LiveDataReactiveStreams.fromPublisher(


                    mainApi.getQuakes("geojson","time",items,minMagnitude,maxMagnitude)

                            .onErrorReturn(new Function<Throwable, Features>() {
                                @Override
                                public Features apply(Throwable throwable) throws Exception {
                                    Log.e(TAG, "apply: ", throwable);
                                    Quake quake = new Quake();
                                    quake.setCityName("-1");
                                    List<Properties> quakes = new ArrayList<>();
                                    Properties properties = new Properties();
                                    properties.setQuakes(quake);
                                    quakes.add(properties);
                                    Features features = new Features();
                                    features.setQuakes(quakes);

                                    return features;
                                }
                            })

                            .map(new Function<Features, Resource<Features>>() {
                                @Override
                                public Resource<Features> apply(Features features) throws Exception {

                                    if(features.getQuakes().size() > 0){
                                        if(features.getQuakes().get(0).getQuake().getCityName().equals("-1")){
                                            return Resource.error("Something went wrong", null);
                                        }
                                    }

                                    return Resource.success(features);
                                }
                            })

                            .subscribeOn(Schedulers.io())
            );

            quakes.addSource(source, new Observer<Resource<Features>>() {
                @Override
                public void onChanged(Resource<Features> listResource) {
                    quakes.setValue(listResource);
                    quakes.removeSource(source);
                }
            });
        }
        return quakes;
    }
}
