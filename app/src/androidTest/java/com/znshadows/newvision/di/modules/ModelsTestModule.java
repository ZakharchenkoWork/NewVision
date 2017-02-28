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
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;


/**
 * Created by kostya on 26.02.2017.
 */

@Module
public class ModelsTestModule {

    @Provides
    @Singleton
    @Named(Constants.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return Schedulers.trampoline();
    }

    @Provides
    @Singleton
    @Named(Constants.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.trampoline();
    }

    @Provides
    @Singleton
    Model provideModel() {
        return mock(Model.class);
    }

    @Singleton
    @Provides
    GitHubApi provideGithubApi(Retrofit builder) {
        return mock(GitHubApi.class);
    }
}
