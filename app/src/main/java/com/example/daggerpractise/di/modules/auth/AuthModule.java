package com.example.daggerpractise.di.modules.auth;

import com.example.daggerpractise.di.scopes.AuthScope;
import com.example.daggerpractise.models.User;
import com.example.daggerpractise.net.AuthApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {

    @AuthScope
    @Named("auth_scope")
    @Provides
    User providersUser(){
        return new User();
    }
    @AuthScope
    @Provides
    static AuthApi providesAuthApi(Retrofit retrofit){
        return retrofit.create(AuthApi.class);
    }
}
