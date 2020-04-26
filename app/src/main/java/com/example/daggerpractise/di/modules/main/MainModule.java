package com.example.daggerpractise.di.modules.main;

import com.example.daggerpractise.di.scopes.MainScope;
import com.example.daggerpractise.net.MainApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @MainScope
    @Provides
     MainApi provideApi(Retrofit retrofit) {
        return retrofit.create(MainApi.class);
    }
}
