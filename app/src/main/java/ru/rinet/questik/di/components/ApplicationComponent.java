package ru.rinet.questik.di.components;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;
import ru.rinet.questik.App;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.di.module.ApplicationModule;
import ru.rinet.questik.di.module.FirebaseModule;
import ru.rinet.questik.di.scope.ApplicationContext;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context getContext();

    Application getApplication();

    DataManager getDataManager();



    void inject(App mvpApp);
}
