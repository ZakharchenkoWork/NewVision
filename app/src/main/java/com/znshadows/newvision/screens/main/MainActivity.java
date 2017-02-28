package com.znshadows.newvision.screens.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.znshadows.newvision.App;
import com.znshadows.newvision.R;
import com.znshadows.newvision.models.Item;

import com.znshadows.newvision.mvp.presenters.IMainPresenter;
import com.znshadows.newvision.mvp.views.MainView;
import com.znshadows.newvision.screens.BaseActivity;
import com.znshadows.newvision.screens.subscribers.SubscribersActivity;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

    @Inject
    IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView okButton = (ImageView) findViewById(R.id.ok);
        RecyclerView itemsContainer = (RecyclerView) findViewById(R.id.itemsContainer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsContainer.setLayoutManager(layoutManager);
        itemsContainer.setHasFixedSize(true);
        initImageLoader();
        okButton.setOnClickListener((v)->{
            Log.d("MainActivity", "click ");
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            presenter.onSearchClick();
        });
    }

    @Override
    protected void injectDependencies() {
        App.getAppComponent().inject(this);
        presenter.setView(this);
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



    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.onStop();
        }
    }

    @Override
    public void showList(List<Item> repoList) {
        RecyclerView itemsContainer = (RecyclerView) findViewById(R.id.itemsContainer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        itemsContainer.setLayoutManager(layoutManager);
        itemsContainer.setHasFixedSize(true);

        DataAdapter adapter = new DataAdapter(repoList, (int position)->{
            Intent detailed = new Intent(MainActivity.this, SubscribersActivity.class);
            detailed.putExtra(SubscribersActivity.EXTRA_SUBSCRIBERS_URL, repoList.get(position).getSubscribersUrl());
            detailed.putExtra(SubscribersActivity.EXTRA_NAME, repoList.get(position).getFullName());
            startActivity(detailed);
        });
        itemsContainer.setAdapter(adapter);

        for (int i = 0; i < repoList.size(); i++) {
            Log.d("MainActivity", "url " + repoList.get(i).getFullName());
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyList() {
        Toast.makeText(this, "no repo", Toast.LENGTH_LONG).show();
    }


    @Override
    public String getUserName() {
        EditText query = (EditText) findViewById(R.id.query);
        return query.getText().toString();
    }

}
