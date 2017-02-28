package com.znshadows.newvision.di.components;



import com.znshadows.newvision.screens.main.MainActivity;
import com.znshadows.newvision.screens.main.MainPresenter;
import com.znshadows.newvision.screens.subscribers.SubscribersActivity;
import com.znshadows.newvision.screens.subscribers.SubscribersPresenter;
import com.znshadows.newvision.di.modules.AppModule;
import com.znshadows.newvision.di.modules.ModelsModule;
import com.znshadows.newvision.di.modules.PresentersModule;
import com.znshadows.newvision.mvp.models.ModelImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by kostya on 25.02.2017.
 */

@Singleton
@Component(modules = {AppModule.class, ModelsModule.class, PresentersModule.class})
public interface AppComponent {

    void inject(ModelImpl model);
    void inject(MainPresenter presenter);
    void inject(SubscribersPresenter presenter);
    void inject(MainActivity activity);
    void inject(SubscribersActivity activity);
}
