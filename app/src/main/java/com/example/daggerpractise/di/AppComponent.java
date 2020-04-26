package com.example.daggerpractise.di;

import android.app.Application;

import com.example.daggerpractise.BaseApplication;
import com.example.daggerpractise.di.modules.ActivityBuildersModule;
import com.example.daggerpractise.di.modules.AppModule;
import com.example.daggerpractise.di.modules.FactoryModule;
import com.example.daggerpractise.di.modules.ViewModelModules;
import com.example.daggerpractise.utils.SessionManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuildersModule.class, AppModule.class,FactoryModule.class, ViewModelModules.class})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    SessionManager sessionManager();
    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder bindApplication(Application application);

        AppComponent build();
    }

}
