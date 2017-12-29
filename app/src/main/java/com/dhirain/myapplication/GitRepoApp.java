package com.dhirain.myapplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Dhirain Jain on 29-12-2017.
 */

public class GitRepoApp extends Application {

    private static GitRepoApp sSingleton;

    public static GitRepoApp singleton() {
        return sSingleton;
    }

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        sSingleton = this;
        context = getApplicationContext();
    }

    public Context getContext() {
        return context;
    }
}
