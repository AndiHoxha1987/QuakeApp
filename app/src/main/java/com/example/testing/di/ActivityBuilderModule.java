package com.example.testing.di;

import com.example.testing.di.main.MainFragmentsBuilderModule;
import com.example.testing.di.main.MainModule;
import com.example.testing.di.main.MainScope;
import com.example.testing.di.main.MainViewModelsModule;
import com.example.testing.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @MainScope
    @ContributesAndroidInjector(
            modules = {MainFragmentsBuilderModule.class, MainViewModelsModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();
}
