package com.znshadows.newvision;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.znshadows.newvision.models.SubscriberData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by kostya on 14.01.2017.
 */

public class DetailedActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_SUBSCRIBERS_URL = "url";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repo_details);
        TextView name = (TextView) findViewById(R.id.name);
        TextView subscribersCount = (TextView) findViewById(R.id.subscribersCount);
     //   TextView subscribersList = (TextView) findViewById(R.id.subscribersList);
        RecyclerView itemsContainer = (RecyclerView) findViewById(R.id.subscribersList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        itemsContainer.setLayoutManager(layoutManager);
        itemsContainer.setHasFixedSize(true);

        if (getIntent().hasExtra(EXTRA_NAME)) {
            name.setText(getIntent().getExtras().getString(EXTRA_NAME));
        }

        if (getIntent().hasExtra(EXTRA_SUBSCRIBERS_URL)) {

            App.getApi().getSubscribers(getIntent().getExtras().getString(EXTRA_SUBSCRIBERS_URL)).enqueue(new Callback<List<SubscriberData>>() {
                @Override
                public void onResponse(Call<List<SubscriberData>> call, Response<List<SubscriberData>> response) {
                    subscribersCount.setText(getString(R.string.subscribers, response.body().size()));
                    SubscribersAdapter adapter = new SubscribersAdapter(response.body());
                    itemsContainer.setAdapter(adapter);
                    /*for (int i = 0; i < response.body().size(); i++) {
                        SubscriberData data = response.body().get(i);
                        subscribersList.append(data.getLogin());

                        if(i != response.body().size()-1){
                            subscribersList.append(", ");
                        }

                    }*/
                }

                @Override
                public void onFailure(Call<List<SubscriberData>> call, Throwable t) {
                    t.printStackTrace();
                }
            });


        }

    }
}
