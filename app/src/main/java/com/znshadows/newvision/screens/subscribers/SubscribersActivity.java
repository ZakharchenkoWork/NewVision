package com.znshadows.newvision.screens.subscribers;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.widget.TextView;

import com.znshadows.newvision.App;
import com.znshadows.newvision.R;
import com.znshadows.newvision.models.SubscriberData;
import com.znshadows.newvision.mvp.presenters.ISubscribersPresenter;
import com.znshadows.newvision.mvp.views.SubscribersView;
import com.znshadows.newvision.screens.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by kostya on 14.01.2017.
 */

public class SubscribersActivity extends BaseActivity implements SubscribersView {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SUBSCRIBERS_URL = "url";
    TextView subscribersCount;
    RecyclerView itemsContainer;

    @Inject
    ISubscribersPresenter subscribersPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_details);

        TextView name = (TextView) findViewById(R.id.name);
        subscribersCount = (TextView) findViewById(R.id.subscribersCount);
        itemsContainer = (RecyclerView) findViewById(R.id.subscribersList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        itemsContainer.setLayoutManager(layoutManager);
        itemsContainer.setHasFixedSize(true);

        if (getIntent().hasExtra(EXTRA_NAME)) {
            name.setText(getIntent().getExtras().getString(EXTRA_NAME));
        }

        if (getIntent().hasExtra(EXTRA_SUBSCRIBERS_URL)) {
            subscribersPresenter.onSubscribersRequest();
        }

    }

    @Override
    protected void injectDependencies() {
        App.getAppComponent().inject(this);
        subscribersPresenter.setView(this);
    }

    @Override
    public void showList(List<SubscriberData> subscriberDatas) {
        subscribersCount.setText(getString(R.string.subscribers, subscriberDatas.size()));
        SubscribersAdapter adapter = new SubscribersAdapter(subscriberDatas);
        itemsContainer.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showEmptyList() {
        itemsContainer.setAdapter(new SubscribersAdapter(new ArrayList<>()));
    }

    @Override
    public String getUrl() {
        if(getIntent().hasExtra(EXTRA_SUBSCRIBERS_URL)){
            return getIntent().getStringExtra(EXTRA_SUBSCRIBERS_URL);
        }
        return "";
    }
}
