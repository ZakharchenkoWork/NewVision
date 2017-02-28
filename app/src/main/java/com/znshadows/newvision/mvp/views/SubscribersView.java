package com.znshadows.newvision.mvp.views;

import com.znshadows.newvision.models.SubscriberData;
import com.znshadows.newvision.mvp.views.BaseView;

import java.util.List;

/**
 * Created by kostya on 04.02.2017.
 */

public interface SubscribersView extends BaseView {
    void showList(List<SubscriberData> subscriberDatas);
    void showEmptyList();
    String getUrl();
}
