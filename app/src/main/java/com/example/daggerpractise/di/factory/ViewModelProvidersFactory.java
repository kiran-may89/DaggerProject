package com.example.daggerpractise.di.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.io.UncheckedIOException;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;


public class ViewModelProvidersFactory implements ViewModelProvider.Factory {

    private Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public ViewModelProvidersFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        Provider<? extends ViewModel> createor = creators.get(modelClass);

        if (createor ==  null) {
            for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())){
                    createor = entry.getValue();
                    break;
                }

            }
        }
        if (createor == null){
            throw new IllegalArgumentException("UNKNOWN Model Class " + modelClass);
        }
        try {
            return (T) createor.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
