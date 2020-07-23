package com.example.testing.ui.main.quakesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.testing.R;
import com.example.testing.model.Features;
import com.example.testing.ui.main.Resource;
import com.example.testing.viewModel.ViewModelProviderFactory;
import javax.inject.Inject;
import javax.inject.Provider;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuakesFragment extends DaggerFragment {

    private static final String TAG = "QuakesFragment";
    private QuakesViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    QuakesRecycleAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    public QuakesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Toast.makeText(getContext(), "Quakes fragment", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_quakes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ViewModel is created");
        viewModel = ViewModelProviders.of(this,providerFactory).get(QuakesViewModel.class);
        recyclerView = view.findViewById(R.id.recycler_view);

        initRecyclerView();
        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.observeQuakes().removeObservers(getViewLifecycleOwner());
        viewModel.observeQuakes().observe(getViewLifecycleOwner(), new Observer<Resource<Features>>() {
            @Override
            public void onChanged(final Resource<Features> listResource) {
                if(listResource != null){
                    switch (listResource.status){

                        case LOADING:{
                            Log.d(TAG, "onChanged: LOADING...");
                            break;
                        }

                        case SUCCESS:{
                            Log.d(TAG, "onChanged: got posts...");
                            assert listResource.data != null;
                            adapter.setPosts(listResource.data.getQuakes());
                            break;
                        }

                        case ERROR:{
                            Log.e(TAG, "onChanged: ERROR..." + listResource.message );
                            break;
                        }
                    }
                }
            }
        });
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}
