package com.znshadows.newvision.di.modules;

import com.znshadows.newvision.screens.main.MainPresenter;
import com.znshadows.newvision.screens.subscribers.SubscribersPresenter;
import com.znshadows.newvision.mvp.presenters.IMainPresenter;
import com.znshadows.newvision.mvp.presenters.ISubscribersPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kostya on 25.02.2017.
 */

@Module
public class PresentersModule {

    @Provides
    @Singleton
    IMainPresenter provideRepoListPresenter(){
        return new MainPresenter();
    }

    @Provides
    @Singleton
    ISubscribersPresenter provideSubscribersPresenter(){
        return new SubscribersPresenter();
    }
}
