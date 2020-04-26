package com.example.daggerpractise.net;

import android.view.View;

import androidx.annotation.Nullable;

public  class AuthResource<T>{

    public T data;
    @NetWorkStatus
    public int status;
    public String message;

    public AuthResource(T data, @NetWorkStatus int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static<T> AuthResource<T> success(@Nullable T data){
        return new AuthResource<>(data,NetWorkStatus.SUCCESS,null);


    }

    public static <T> AuthResource<T> error(@Nullable String message, @Nullable T data){
        return  new AuthResource<>(data,NetWorkStatus.ERROR,message);

    }
    public static <T> AuthResource<T> loading( T data){
        return  new AuthResource<>(data,NetWorkStatus.LOADING,null);
    }

    public static <T> AuthResource<T> logout () {
        return  new AuthResource<>(null,NetWorkStatus.NOT_AUTHENTICATED,null);
    }
}



