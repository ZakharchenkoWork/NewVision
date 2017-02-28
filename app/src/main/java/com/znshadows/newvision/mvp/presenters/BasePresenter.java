package com.znshadows.newvision.mvp.presenters;

import com.znshadows.newvision.mvp.views.BaseView;

/**
 * Created by kostya on 26.02.2017.
 */

public interface BasePresenter<T extends BaseView> {
    void onStop();
    void setView(T view);
}
