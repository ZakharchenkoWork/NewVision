package com.znshadows.newvision.di.modules;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kostya on 25.02.2017.
 */

@Module
public class AppTestModule {
    private static final String GITHUB_URL = "https://api.github.com/";
    private static final int NETWORK_TIMEOUT_SEC = 10;

    @Singleton
    @Provides
    OkHttpClient provideClient(){
        return null;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient client){
        return null;
    }


}

