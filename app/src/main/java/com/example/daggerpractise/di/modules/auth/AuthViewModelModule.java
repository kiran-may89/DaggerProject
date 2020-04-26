package com.example.daggerpractise.di.modules.auth;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.ui.auth.AuthViewModel;
import com.example.daggerpractise.di.scopes.ViewModelScope;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public  abstract  class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScope(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
