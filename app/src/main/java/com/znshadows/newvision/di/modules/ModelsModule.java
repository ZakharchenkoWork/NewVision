package com.znshadows.newvision.di.modules;

import com.google.googlejavaformat.Indent;
import com.znshadows.newvision.mvp.models.GitHubApi;
import com.znshadows.newvision.mvp.models.Model;
import com.znshadows.newvision.mvp.models.ModelImpl;
import com.znshadows.newvision.utils.Constants;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by kostya on 25.02.2017.
 */

@Module
public class ModelsModule {
    @Provides
    @Singleton
    Model provideModel(){
        return new ModelImpl();
    }
    @Singleton
    @Provides
    GitHubApi provideGithubApi(Retrofit builder){
        return builder.create(GitHubApi.class);
    }

    @Provides
    @Singleton
    @Named(Constants.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Constants.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }
}
