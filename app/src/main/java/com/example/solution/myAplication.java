package com.example.solution;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class myAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this
        );
    }
}
