package com.znshadows.newvision;

import android.app.Application;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.znshadows.newvision.di.components.AppComponent;
import com.znshadows.newvision.di.components.DaggerAppComponent;

import com.znshadows.newvision.mvp.models.GitHubApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kostya on 07.01.2017.
 */

public class App extends Application {

    private static AppComponent appComponent;


    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();


    }

    protected void initComponent() {
        appComponent = DaggerAppComponent.create();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
