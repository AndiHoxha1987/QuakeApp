package com.example.testing.di.main;

import androidx.lifecycle.ViewModel;

import com.example.testing.di.ViewModelKey;
import com.example.testing.ui.main.quakesFragment.QuakesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(QuakesViewModel.class)
    public abstract ViewModel bindQuakesViewModel(QuakesViewModel viewModel);



}
