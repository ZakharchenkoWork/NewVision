package com.znshadows.newvision.mvp.models;

import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.models.SubscriberData;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by kostya on 04.02.2017.
 */

public interface Model {

        Observable<ReposData> getRepoList(String name);
        Observable<List<SubscriberData>> getSubscriberList(String urls);

}
