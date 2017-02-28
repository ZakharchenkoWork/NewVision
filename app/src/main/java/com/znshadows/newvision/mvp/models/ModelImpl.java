package com.znshadows.newvision.mvp.models;


import com.znshadows.newvision.App;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.models.SubscriberData;
import com.znshadows.newvision.utils.Constants;

import java.util.List;


import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by kostya on 04.02.2017.
 */

public class ModelImpl implements Model {

    @Inject
    GitHubApi apiInterface;

    @Inject
    @Named(Constants.IO_THREAD)
    Scheduler ioScheduler;

    @Inject
    @Named(Constants.UI_THREAD)
    Scheduler uiScheduler;

    public ModelImpl() {
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<ReposData> getRepoList(String name) {
        return apiInterface.getData(name)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }

    @Override
    public Observable<List<SubscriberData>> getSubscriberList(String url) {
        return apiInterface.getSubscribers(url)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }
}
