package com.example.daggerpractise.net;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({NetWorkStatus.SUCCESS, NetWorkStatus.ERROR, NetWorkStatus.LOADING, NetWorkStatus.NOT_AUTHENTICATED})
public @interface NetWorkStatus {
    int SUCCESS = 0;
    int ERROR = 1;
    int LOADING = 2;
    int NOT_AUTHENTICATED = 3;

}
