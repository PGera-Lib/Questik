package ru.rinet.questik.di.module;


import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.di.scope.ActivityContext;
import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.login.LoginMvpPresenter;
import ru.rinet.questik.ui.login.LoginMvpView;
import ru.rinet.questik.ui.login.LoginPresenter;
import ru.rinet.questik.ui.login.dialog.AuthDialogFragmentMvpPresenter;
import ru.rinet.questik.ui.login.dialog.AuthDialogFragmentMvpView;
import ru.rinet.questik.ui.login.dialog.AuthDialogFragmentPresenter;
import ru.rinet.questik.ui.main.MainMvpPresenter;
import ru.rinet.questik.ui.main.MainMvpView;
import ru.rinet.questik.ui.main.MainPresenter;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragmentAdapter;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragmentMvpPresenter;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragmentMvpView;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragmentPresenter;
import ru.rinet.questik.ui.main.catalog.fragment.addedit.CatalogAddEditFragmentMvpPresenter;
import ru.rinet.questik.ui.main.catalog.fragment.addedit.CatalogAddEditFragmentMvpView;
import ru.rinet.questik.ui.main.catalog.fragment.addedit.CatalogAddEditFragmentPresenter;
import ru.rinet.questik.ui.main.project.ProjectFragmentAdapter;
import ru.rinet.questik.ui.main.project.ProjectFragmentMvpPresenter;
import ru.rinet.questik.ui.main.project.ProjectFragmentMvpView;
import ru.rinet.questik.ui.main.project.ProjectFragmentPresenter;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerAdapter;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerMvpPresenter;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerMvpView;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerPresenter;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragmentMvpPresenter;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragmentMvpView;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragmentPresenter;
import ru.rinet.questik.ui.main.project.fragment.itog.ProjectItogMvpPresenter;
import ru.rinet.questik.ui.main.project.fragment.itog.ProjectItogMvpView;
import ru.rinet.questik.ui.main.project.fragment.itog.ProjectItogPresenter;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobAdapter;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobMvpPresenter;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobMvpView;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobPresenter;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialAdapter;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialMvpPresenter;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialMvpView;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialPresenter;
import ru.rinet.questik.ui.splash.SplashMvpPresenter;
import ru.rinet.questik.ui.splash.SplashMvpView;
import ru.rinet.questik.ui.splash.SplashPresenter;
import ru.rinet.questik.utils.rx.AppSchedulerProvider;
import ru.rinet.questik.utils.rx.SchedulerProvider;



@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

/*    @Singleton
    @Provides
    @FirebaseInfo
    public DatabaseReference provideDatabaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    @Singleton
    @Provides
    @FirebaseInfo
    public FirebaseAuth provideFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @Nullable
    @Singleton
    @Provides
    @FirebaseInfo
    public FirebaseUser provideCurrentUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }*/


    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideScheduleProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(activity);
    }

    /**
    провайдим пресентеры
     */

    @Provides
    @PerActivity
    SplashMvpPresenter<SplashMvpView> provideSplashPresenter(SplashPresenter<SplashMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    LoginMvpPresenter<LoginMvpView> provideLoginPresenter(LoginPresenter<LoginMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> providesMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }




    //Catalog
    @Provides
    @PerActivity
    CatalogFragmentAdapter providesCatalogFragmentAdapter() {
        return new CatalogFragmentAdapter(new ArrayList<Job>());
    }

    @Provides
    @PerActivity
    CatalogFragmentMvpPresenter<CatalogFragmentMvpView> providesCatalogPresenter(CatalogFragmentPresenter<CatalogFragmentMvpView> presenter) {
        return presenter;
    }

    /**
     * Проекты
     */
    @Provides
    @PerActivity
    ProjectFragmentAdapter providesProjectFragmentAdapter() {
        return new ProjectFragmentAdapter(new ArrayList<Project>());
    }

    @Provides
    @PerActivity
    ProjectFragmentMvpPresenter<ProjectFragmentMvpView> providesProjectPresenter(ProjectFragmentPresenter<ProjectFragmentMvpView> presenter) {
        return presenter;
    }

    /**
     * Detail Project Activity
    */
    @Provides
    @PerActivity
    DetailProjectPagerAdapter providesDetailProjectPagerAdapter(AppCompatActivity activity) {
        return new DetailProjectPagerAdapter(activity.getSupportFragmentManager());
    }

    @Provides
    @PerActivity
    DetailProjectPagerMvpPresenter<DetailProjectPagerMvpView> provideDetailProjectPresenter(DetailProjectPagerPresenter<DetailProjectPagerMvpView> presenter) {
        return presenter;
    }


    /**
     * Project Detail Fragment
     */
    @Provides
    @PerActivity
    ProjectDetailFragmentMvpPresenter<ProjectDetailFragmentMvpView> provideProjectDetailFragmentPresenter(ProjectDetailFragmentPresenter<ProjectDetailFragmentMvpView> presenter) {
        return presenter;
    }

    /**
     * Project Detail Fragment
     */
    @Provides
    @PerActivity
    ProjectItogMvpPresenter<ProjectItogMvpView> provideProjectItogPresenter(ProjectItogPresenter<ProjectItogMvpView> presenter) {
        return presenter;
    }

    /**
     * Project Detail Job Fragment
     */
    @Provides
    @PerActivity
    ProjectDetailJobMvpPresenter<ProjectDetailJobMvpView> provideProjectDetailJobPresenter(ProjectDetailJobPresenter<ProjectDetailJobMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    ProjectDetailJobAdapter providesProjectDetailJobAdapter() {
        return new ProjectDetailJobAdapter(new ArrayList<Job>());
    }

    /**
     * Project Detail Material Fragment
     */
    @Provides
    @PerActivity
    ProjectDetailMaterialMvpPresenter<ProjectDetailMaterialMvpView> provideProjectDetailMaterialPresenter(ProjectDetailMaterialPresenter<ProjectDetailMaterialMvpView> presenter) {
        return presenter;
    }
    @Provides
    @PerActivity
    ProjectDetailMaterialAdapter providesProjectDetailMaterialdapter() {
        return new ProjectDetailMaterialAdapter(new ArrayList<Job>());
    }
    /**
     * Dialog Catalog
     */

    @Provides
    @PerActivity
    CatalogAddEditFragmentMvpPresenter<CatalogAddEditFragmentMvpView> provideCatalogDialogPresenter(
            CatalogAddEditFragmentPresenter<CatalogAddEditFragmentMvpView> presenter) {
        return presenter;
    }

    /**
     * Dialog Firebase Auth
     */

    @Provides
    @PerActivity
    AuthDialogFragmentMvpPresenter<AuthDialogFragmentMvpView> provideAuthDialogFragmentPresenter(
            AuthDialogFragmentPresenter< AuthDialogFragmentMvpView> presenter) {
        return presenter;
    }



/**
 * ToDo: поэтапной заполняем модули
 */




/*
    @Provides
    @PerActivity
    MainMvpPresenter<ChatMvpView> providesChatPresenter(MainPresenter<ChatMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<ChernMvpView> providesChernPresenter(MainPresenter<ChernMvpView> presenter) {
        return presenter;
    }
*/



}
