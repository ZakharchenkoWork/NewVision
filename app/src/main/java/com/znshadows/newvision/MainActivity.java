package com.znshadows.newvision;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.znshadows.newvision.models.ReposData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView okButton = (ImageView) findViewById(R.id.ok);
        EditText query = (EditText) findViewById(R.id.query);
        RecyclerView itemsContainer = (RecyclerView) findViewById(R.id.itemsContainer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsContainer.setLayoutManager(layoutManager);
        itemsContainer.setHasFixedSize(true);
        initImageLoader();
        okButton.setOnClickListener((v)->{
            Log.d("MainActivity", "click ");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

            App.getApi().getData(query.getText().toString()).enqueue(new Callback<ReposData>() {
                @Override
                public void onResponse(Call<ReposData> call, Response<ReposData> response) {
                    Log.d("MainActivity", "url " + call.request().url());

                    DataAdapter adapter = new DataAdapter(response.body().getItems(), (int position)->{
                        Intent detailed = new Intent(MainActivity.this, DetailedActivity.class);
                        detailed.putExtra(DetailedActivity.EXTRA_SUBSCRIBERS_URL, response.body().getItems().get(position).getSubscribersUrl());
                        detailed.putExtra(DetailedActivity.EXTRA_NAME, response.body().getItems().get(position).getFullName());
                        startActivity(detailed);
                    });
                    itemsContainer.setAdapter(adapter);

                    for (int i = 0; i < response.body().getItems().size(); i++) {
                        Log.d("MainActivity", "url " + response.body().getItems().get(i).getFullName());
                    }
                }

                @Override
                public void onFailure(Call<ReposData> call, Throwable t) {
                    Log.d("MainActivity", "url " + call.request().url());
                    t.printStackTrace();
                }
            });

        });


    }
    private void initImageLoader(){
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.no_image) // resource or drawable
                .resetViewBeforeLoading(false)  // default
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(displayMetrics.widthPixels, displayMetrics.heightPixels) // default = device screen dimensions
                .denyCacheImageMultipleSizesInMemory()
                .writeDebugLogs()
                .defaultDisplayImageOptions(options)
                .build();
        ImageLoader.getInstance().init(config);
    }
}
