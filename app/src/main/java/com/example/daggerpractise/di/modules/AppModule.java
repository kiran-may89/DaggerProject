package com.example.daggerpractise.di.modules;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.daggerpractise.R;
import com.example.daggerpractise.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    RequestOptions providesRequestOption(){
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background);
    }


    @Singleton
    @Provides
     RequestManager providesRequestManager(Application application, RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }


    @Singleton
    @Provides
     Retrofit providesRetrofit(){
        return  new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
