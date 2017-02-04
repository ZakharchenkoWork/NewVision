package com.znshadows.newvision.models;


import com.znshadows.newvision.App;
import com.znshadows.newvision.GitHubApi;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by kostya on 04.02.2017.
 */

public class ModelImpl implements Model{
    GitHubApi apiInterface = App.getApi();

    @Override
    public Observable<ReposData> getRepoList(String name) {
        return apiInterface.getData(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }
}
