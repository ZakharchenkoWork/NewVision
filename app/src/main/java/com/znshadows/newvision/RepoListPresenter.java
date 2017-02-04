package com.znshadows.newvision;

import android.view.View;

import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.Model;
import com.znshadows.newvision.models.ModelImpl;
import com.znshadows.newvision.models.ReposData;


import org.reactivestreams.Subscription;


import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by kostya on 04.02.2017.
 */

public class RepoListPresenter implements Presenter {

    private Model model = new ModelImpl();

    private IView view;
    Disposable disposable = null;

    public RepoListPresenter(IView view) {
        this.view = view;
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
}

