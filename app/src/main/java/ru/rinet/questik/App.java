package ru.rinet.questik;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import javax.inject.Inject;

import ru.rinet.questik.di.components.DaggerApplicationComponent;
import ru.rinet.questik.di.components.ApplicationComponent;
import ru.rinet.questik.di.module.ApplicationModule;
import ru.rinet.questik.di.module.FirebaseModule;
import ru.rinet.questik.utils.AppLogger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class App extends Application {

    @Inject
    CalligraphyConfig calligraphyConfig;

/*
    @Inject
    FirebaseAuth firebaseAuth;

    @Inject
    FirebaseUser user;

    @Inject
    DatabaseReference reference;

*/


    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        //Instantiate ApplicationComponent
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        applicationComponent.inject(this);

        AppLogger.init();

        /*
         * Init Facebook SDK*/

        CalligraphyConfig.initDefault(calligraphyConfig);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
