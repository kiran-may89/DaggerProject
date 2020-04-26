package com.example.daggerpractise.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.example.daggerpractise.di.factory.ViewModelProvidersFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class FactoryModule {
    @Binds
    abstract ViewModelProvider.Factory bindFactory(ViewModelProvidersFactory factory);
}
