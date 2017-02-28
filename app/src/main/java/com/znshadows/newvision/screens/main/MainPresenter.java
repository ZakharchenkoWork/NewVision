package com.znshadows.newvision.screens.main;

import com.znshadows.newvision.App;
import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.mvp.models.Model;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.mvp.presenters.IMainPresenter;
import com.znshadows.newvision.mvp.views.MainView;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by kostya on 04.02.2017.
 */

public class MainPresenter implements IMainPresenter<MainView> {

    @Inject
    Model model;


    private MainView view;

    Disposable disposable = null;

    public MainPresenter() {
        App.getAppComponent().inject(this);
    }

    @Override
    public void onSearchClick() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        model.getRepoList(view.getUserName())
                .subscribe(new Observer<ReposData>() {
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
                    public void onNext(ReposData reposData) {
                        List<Item> data = reposData.getItems();

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
    public void setView(MainView view) {
        this.view = view;
    }
}

