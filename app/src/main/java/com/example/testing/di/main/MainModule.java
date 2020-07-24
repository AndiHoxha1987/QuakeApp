package com.example.testing.di.main;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.network.main.MainApi;
import com.example.testing.ui.main.quakesFragment.QuakesRecycleAdapter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

//TODO MainScope

@Module
public class MainModule {


    @MainScope
    @Provides
    static QuakesRecycleAdapter provideAdapter(){
        return new QuakesRecycleAdapter();
    }



    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

}