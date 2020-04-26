package com.example.daggerpractise.di.modules.main;

import com.example.daggerpractise.ui.main.PostFragment;
import com.example.daggerpractise.ui.main.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

}
