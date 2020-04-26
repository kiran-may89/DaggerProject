package com.example.daggerpractise.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.models.User;
import com.example.daggerpractise.net.AuthApi;
import com.example.daggerpractise.net.AuthResource;
import com.example.daggerpractise.utils.SessionManager;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private final AuthApi authApi;

    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(AuthApi authApi,SessionManager sessionManager) {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "AuthViewModel: viewmodel is working...");

    }

    public void authenticateWithId(int userId) {
        sessionManager.authenticateWithId(queryUserId(userId));


    }

    public LiveData<AuthResource<User>> queryUserId(int userId) {

        return LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId)
                .onErrorReturn(throwable -> {
                    User errorUser = new User();
                    errorUser.setId(-1);
                    return errorUser;
                })

                // wrap User object in AuthResource
                .map((Function<User, AuthResource<User>>) user -> {
                    if (user.getId() == -1) {
                        return AuthResource.error("Could not authenticate", null);
                    }
                    return AuthResource.success(user);
                })
                .subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return sessionManager.getUserLiveData();
    }
}



