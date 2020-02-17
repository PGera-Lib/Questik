package ru.rinet.questik;

import android.app.Application;

import androidx.room.Room;

import ru.rinet.questik.data.room.AppDatabase;

public class App extends Application {

    public static final String DATABASE = "questik.db";

    public static App instance;

    private AppDatabase database;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, DATABASE)
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
    public void setDatabase(final String database) {
        this.database = Room.databaseBuilder(this, AppDatabase.class, database).build();
    }

}
