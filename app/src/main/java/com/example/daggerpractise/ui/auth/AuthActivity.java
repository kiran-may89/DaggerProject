package com.example.daggerpractise.ui.auth;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.daggerpractise.models.User;
import com.example.daggerpractise.ui.main.MainActivity;
import com.example.daggerpractise.R;
import com.example.daggerpractise.di.factory.ViewModelProvidersFactory;
import com.example.daggerpractise.net.NetWorkStatus;
import com.example.daggerpractise.utils.SessionManager;
import com.google.android.material.textfield.TextInputEditText;


import javax.inject.Inject;
import javax.inject.Named;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    private static final String TAG = "MainActivity";

    @Inject
    ViewModelProvidersFactory providerFactory;

    @Inject
    SessionManager sessionManager;

    AuthViewModel authViewModel;

    @Inject
    @Named("auth_scope")
    User injectedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: " + injectedUser);
        DataBindingUtil.setContentView(this, R.layout.activity_auth);
        authViewModel = ViewModelProviders.of(this, providerFactory).get(AuthViewModel.class);
        // requestManager.load(R.drawable.logo).into(binding.loginLogo);

        findViewById(R.id.login_button).setOnClickListener(view -> {

            String text = ((TextInputEditText) findViewById(R.id.user_id_input)).getText().toString();

            authViewModel.authenticateWithId(Integer.parseInt(text));
        });
        authViewModel.getAuthUser().observe(this, response -> {
            if (response != null) {
                switch (response.status) {
                    case NetWorkStatus.SUCCESS:

                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        break;
                }
            }
        });

    }
}
