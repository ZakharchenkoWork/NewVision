package com.znshadows.newvision;

import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.SubscriberData;

import java.util.List;

/**
 * Created by kostya on 04.02.2017.
 */

public interface IView2 {
    void showList(List<SubscriberData> subscriberDatas);
    void showError(String error);
    void showEmptyList();
    String getUrl();
}
