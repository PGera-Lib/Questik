package ru.rinet.questik.di.module;

import android.app.Application;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.rinet.questik.R;
import ru.rinet.questik.data.AppDataManager;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.prefs.AppPreferenceHelper;
import ru.rinet.questik.data.prefs.PreferenceHelper;
import ru.rinet.questik.data.retrofit.ApiCall;
import ru.rinet.questik.data.retrofit.ApiHelper;
import ru.rinet.questik.data.retrofit.AppApiHelper;
import ru.rinet.questik.data.room.AppDatabase;
import ru.rinet.questik.data.room.AppDbHelper;
import ru.rinet.questik.data.room.DbHelper;
import ru.rinet.questik.di.scope.ApplicationContext;
import ru.rinet.questik.di.scope.DatabaseInfo;
import ru.rinet.questik.di.scope.PreferenceInfo;
import ru.rinet.questik.utils.Constants;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;



@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return application;
    }

    @Provides
    Application providesApplication() {
        return application;
    }

    @Provides
    @DatabaseInfo
    String providesDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @DatabaseInfo
    Integer providesDatabaseVersion() {
        return Constants.DB_VERSION;
    }

    @Provides
    @PreferenceInfo
    String providesSharedPrefName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager providesDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper providesDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    ApiHelper providesApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    PreferenceHelper providesPreferenceHelper(AppPreferenceHelper appPreferenceHelper) {
        return appPreferenceHelper;
    }

    @Provides
    @Singleton
    ApiCall providesApiCall() {
        return ApiCall.Factory.create();
    }


    @Provides
    @Singleton
    AppDatabase providesAppDatabase(@ApplicationContext Context context,
                                    @DatabaseInfo String dbName) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,
                dbName).fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    CalligraphyConfig providesCalligraphyConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }





}
