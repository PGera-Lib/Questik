package ru.rinet.questik.di.module;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.rinet.questik.di.scope.PerActivity;

@Module
public class FirebaseModule {

/*    @Nullable
    @PerActivity*/
    @Provides
    public FirebaseUser provideCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }


    @PerActivity
    @Provides
    public DatabaseReference provideDatabaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }


    @PerActivity
    @Provides
    public FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }
}