package com.znshadows.newvision;

import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.Model;
import com.znshadows.newvision.models.ModelImpl;
import com.znshadows.newvision.models.ModelSubscribers;
import com.znshadows.newvision.models.ModelSuscribersImpl;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.models.SubscriberData;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by kostya on 04.02.2017.
 */

public class SubscriberPresenter implements Presenter2 {

    private ModelSubscribers model = new ModelSuscribersImpl();

    private IView2 view;
    Disposable disposable = null;

    public SubscriberPresenter(IView2 view) {
        this.view = view;
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
}

