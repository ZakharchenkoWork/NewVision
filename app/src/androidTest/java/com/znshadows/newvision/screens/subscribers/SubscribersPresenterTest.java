package com.znshadows.newvision.screens.subscribers;

import com.znshadows.newvision.App;
import com.znshadows.newvision.TestApplication;
import com.znshadows.newvision.models.SubscriberData;
import com.znshadows.newvision.mvp.models.Model;
import com.znshadows.newvision.mvp.presenters.ISubscribersPresenter;
import com.znshadows.newvision.mvp.views.SubscribersView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by kostya on 04.02.2017.
 */

public class SubscribersPresenterTest implements ISubscribersPresenter<SubscribersView> {
    @Inject
    Model model;

    private SubscribersView view;
    Disposable disposable = null;

    public SubscribersPresenterTest() {

        TestApplication.getTestAppComponent().inject(this);
    }


    @Override
    public void onSubscribersRequest() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        model.getSubscriberList(view.getUrl())
                .subscribe(new Observer<List<SubscriberData>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(List<SubscriberData> data) {

                        if (data != null && !data.isEmpty()) {
                            view.showList(data);
                        } else {
                            view.showEmptyList();
                        }
                    }

                });
    }

    @Override
    public void onStop() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void setView(SubscribersView view) {
        this.view = view;   
    }
}

