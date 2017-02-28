package com.znshadows.newvision.mvp.presenters;
import com.znshadows.newvision.mvp.views.BaseView;
import com.znshadows.newvision.mvp.views.SubscribersView;

/**
 * Created by kostya on 04.02.2017.
 */

public interface ISubscribersPresenter<T extends BaseView> extends BasePresenter<T> {
        void onSubscribersRequest();

        void setView(SubscribersView view);
}
