package com.example.testing.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.testing.viewModel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory
    bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);
}
