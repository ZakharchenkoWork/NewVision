package com.znshadows.newvision.di.components;


import com.znshadows.newvision.di.modules.AppModule;
import com.znshadows.newvision.di.modules.AppTestModule;
import com.znshadows.newvision.di.modules.ModelsModule;
import com.znshadows.newvision.di.modules.ModelsTestModule;
import com.znshadows.newvision.di.modules.PresentersModule;
import com.znshadows.newvision.di.modules.PresentersModuleTest;
import com.znshadows.newvision.mvp.models.ModelImpl;
import com.znshadows.newvision.mvp.models.ModelImplTest;
import com.znshadows.newvision.screens.main.MainActivity;
import com.znshadows.newvision.screens.main.MainActivityTest;
import com.znshadows.newvision.screens.main.MainPresenter;
import com.znshadows.newvision.screens.main.MainPresenterTest;
import com.znshadows.newvision.screens.subscribers.SubscribersActivity;
import com.znshadows.newvision.screens.subscribers.SubscribersActivityTest;
import com.znshadows.newvision.screens.subscribers.SubscribersPresenter;
import com.znshadows.newvision.screens.subscribers.SubscribersPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kostya on 25.02.2017.
 */

@Singleton
@Component(modules = {AppTestModule.class, ModelsTestModule.class, PresentersModuleTest.class})
//{ModelTestModule.class, PresenterTestModule.class, ViewTestModule.class, DataTestModule.class})
public interface TestComponent {

    void inject(ModelImplTest model);
    void inject(MainPresenterTest presenter);
    void inject(SubscribersPresenterTest presenter);
    void inject(MainActivityTest activity);
    void inject(SubscribersActivityTest activity);
}
