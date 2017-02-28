package com.znshadows.newvision.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kostya on 26.02.2017.
 */

public abstract class BaseActivityTest  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();

    }

    protected abstract void injectDependencies();


}
