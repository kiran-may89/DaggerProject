package com.example.daggerpractise.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.daggerpractise.models.User;
import com.example.daggerpractise.net.AuthApi;
import com.example.daggerpractise.net.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData();

    private AuthApi authApi;

    @Inject
    public SessionManager() {


    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source) {

        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading((User) null));


            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });

        }

    }
    public boolean isLoggedIn(){
        return  (cachedUser != null && cachedUser.getValue() !=null);
    }

    public void logOut(){
        cachedUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getUserLiveData() {
        return cachedUser;
    }

}
