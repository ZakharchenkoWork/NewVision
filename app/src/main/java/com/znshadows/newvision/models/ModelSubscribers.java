package com.znshadows.newvision.models;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by kostya on 04.02.2017.
 */

public interface ModelSubscribers {

        Observable<List<SubscriberData>> getSubscriberList(String urls);

}
