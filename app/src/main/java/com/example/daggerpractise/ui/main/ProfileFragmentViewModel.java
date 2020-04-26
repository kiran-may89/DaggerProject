package com.example.daggerpractise.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.models.User;
import com.example.daggerpractise.net.AuthResource;
import com.example.daggerpractise.utils.SessionManager;

import javax.inject.Inject;

public class ProfileFragmentViewModel extends ViewModel {
    private final SessionManager sessionManager;

    @Inject
    public ProfileFragmentViewModel(SessionManager sessionManager){
        this.sessionManager = sessionManager;

    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getUserLiveData();
    }
}
