package com.example.daggerpractise.di.modules.main;

import androidx.lifecycle.ViewModel;

import com.example.daggerpractise.di.scopes.ViewModelScope;
import com.example.daggerpractise.ui.main.PostFragmentViewModel;
import com.example.daggerpractise.ui.main.ProfileFragment;
import com.example.daggerpractise.ui.main.ProfileFragmentViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScope(PostFragmentViewModel.class)
    abstract ViewModel bindPostFragmentViewModel(PostFragmentViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelScope(ProfileFragmentViewModel.class)
    abstract ViewModel bindProfileFragmentViewModel(ProfileFragmentViewModel viewModel);
}
