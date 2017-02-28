package com.znshadows.newvision;

import com.znshadows.newvision.di.components.AppComponent;
import com.znshadows.newvision.di.components.DaggerTestComponent;
import com.znshadows.newvision.di.components.TestComponent;

public class TestApplication extends App {
    static TestComponent appComponent;
    public static TestComponent getTestAppComponent() {
        return appComponent;
    }

    @Override
    protected void initComponent() {
        appComponent = DaggerTestComponent.create();
    }

}