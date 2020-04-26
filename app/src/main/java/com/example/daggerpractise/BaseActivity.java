package com.example.daggerpractise;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.daggerpractise.net.NetWorkStatus;
import com.example.daggerpractise.ui.auth.AuthActivity;
import com.example.daggerpractise.utils.SessionManager;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public  abstract class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Inject
    SessionManager sessionManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        listenUserSession();
    }

    public void listenUserSession() {
        sessionManager.getUserLiveData().observe(this, session -> {

            if (session != null) {
                switch (session.status) {
                    case NetWorkStatus.LOADING:
                        Log.d(TAG, "listenUserSession: NetWorkStatus.LOADING");
                        break;
                    case NetWorkStatus.ERROR:
                        Log.d(TAG, "listenUserSession: NetWorkStatus.ERROR");
                        break;
                    case NetWorkStatus.NOT_AUTHENTICATED:
                        navigateToAuth();
                        break;

                }

            }

        });
    }

    public void navigateToAuth() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }

}
