package com.znshadows.newvision.mvp.views;

import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.mvp.views.BaseView;

import java.util.List;

/**
 * Created by kostya on 04.02.2017.
 */

public interface MainView extends BaseView {
    void showList(List<Item> repoList);

    void showEmptyList();
    String getUserName();

}
