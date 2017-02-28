package com.znshadows.newvision.mvp.presenters;


import com.znshadows.newvision.mvp.views.BaseView;
import com.znshadows.newvision.mvp.views.MainView;

/**
 * Created by kostya on 04.02.2017.
 */

public interface IMainPresenter<T extends BaseView> extends BasePresenter<T> {
        void onSearchClick();

}
