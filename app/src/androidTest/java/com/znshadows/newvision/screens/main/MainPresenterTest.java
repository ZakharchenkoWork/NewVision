package com.znshadows.newvision.screens.main;

import com.google.gson.Gson;
import com.znshadows.newvision.App;
import com.znshadows.newvision.TestApplication;
import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.ReposData;
import com.znshadows.newvision.mvp.models.Model;
import com.znshadows.newvision.mvp.presenters.IMainPresenter;
import com.znshadows.newvision.mvp.views.MainView;
import com.znshadows.newvision.utils.TestConst;
import com.znshadows.newvision.utils.TestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;


/**
 * Created by kostya on 04.02.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest implements IMainPresenter<MainView> {

    @Mock
    Model model;

    @Mock
    private MainView view;

    Disposable disposable = null;

    public MainPresenterTest() {
        TestApplication.getTestAppComponent().inject(this);
    }

    @Test
    @Override
    public void onSearchClick() {

        ReposData repos = new Gson().fromJson(new TestUtils().readString(TestConst.TEST_JSON), ReposData.class);
        when(model.getRepoList(TestConst.TEST_REPO)).thenReturn(Observable.just(repos));
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
                        assertEquals(reposData.getItems().size(), 30);
                        List<Item> data = reposData.getItems();

    /*                    if (data != null && !data.isEmpty()) {
                            view.showList(data);
                        } else {
                            view.showEmptyList();
                        }*/
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

