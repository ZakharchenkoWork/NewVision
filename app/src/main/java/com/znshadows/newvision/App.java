package com.znshadows.newvision;

import android.app.Application;
import android.util.DisplayMetrics;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kostya on 07.01.2017.
 */

public class App extends Application {

    private static GitHubApi gitHubApi;


    public static final String GITHUB_URL = "https://api.github.com/";

    private static Retrofit builder;

    @Override
    public void onCreate() {
        super.onCreate();
        getApi();
    }



    public static GitHubApi getApi() {
        if (builder != null) {
            return gitHubApi;
        } else {
            builder = new Retrofit.Builder()
                    .baseUrl(GITHUB_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build();
            gitHubApi = builder.create(GitHubApi.class);
            return gitHubApi;
        }

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

    }
}
