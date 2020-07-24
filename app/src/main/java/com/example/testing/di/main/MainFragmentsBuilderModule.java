package com.example.testing.di.main;

import com.example.testing.ui.main.quakesFragment.QuakesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentsBuilderModule {

    @ContributesAndroidInjector
    abstract QuakesFragment contributePostsFragment();
}


