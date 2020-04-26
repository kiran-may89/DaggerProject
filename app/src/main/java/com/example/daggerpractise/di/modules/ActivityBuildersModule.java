package com.example.daggerpractise.di.modules;

import com.example.daggerpractise.di.modules.main.FragmentBuilderModule;
import com.example.daggerpractise.di.modules.main.MainModule;
import com.example.daggerpractise.di.modules.main.MainViewModelModule;
import com.example.daggerpractise.di.scopes.AuthScope;
import com.example.daggerpractise.di.scopes.MainScope;
import com.example.daggerpractise.ui.auth.AuthActivity;
import com.example.daggerpractise.ui.main.MainActivity;
import com.example.daggerpractise.di.modules.auth.AuthModule;
import com.example.daggerpractise.di.modules.auth.AuthViewModelModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();


    @MainScope
    @ContributesAndroidInjector(modules = {FragmentBuilderModule.class, MainModule.class, MainViewModelModule.class})
    abstract MainActivity contributeMainActivity();
}


