package ru.rinet.questik.ui.main;



import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;


public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {


    @Inject
    public MainPresenter(SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable,
                         DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached())
            return;

        String stringHolder;

        stringHolder = getDataManager().getCurrentUserName();
        if (stringHolder != null && !stringHolder.isEmpty())
            getMvpView().updateUserName(stringHolder);

        stringHolder = getDataManager().getCurrentUserEmail();
        if (stringHolder != null && !stringHolder.isEmpty())
            getMvpView().updateUserEmail(stringHolder);
    }

    @Override
    public void onDrawerOptionHomeClicked() {

        Log.i("MainPresenter", "Home");
    }

    @Override
    public void onDrawerOptionProjectClicked() {

        Log.i("MainPresenter", "Project");
    }

    @Override
    public void onDrawerOptionCatalogClicked() {
        getMvpView().closeNavigationDrawer();
       // checkJobAvailableInDb();
        Log.i("MainPresenter", "Catalog");
    }

    @Override
    public void onDrawerOptionChernClicked() {

        Log.i("MainPresenter", "Chern");
    }

    @Override
    public void onDrawerOptionDocClicked() {

        Log.i("MainPresenter", "Doc");
    }

    @Override
    public void onDrawerOptionHowToClicked() {

        Log.i("MainPresenter", "HowTo");
    }

    @Override
    public void onDrawerOptionGameClicked() {

        Log.i("MainPresenter", "Game");
    }

    @Override
    public void onDrawerOptionChatClicked() {

        Log.i("MainPresenter", "Chat");
    }

    @Override
    public void onDrawerOptionSettingsClicked() {

        Log.i("MainPresenter", "Options");
    }



    @Override
    public void onDrawerOptionLogoutClicked() {
        getMvpView().showLoading();

        getDataManager().setCurrentUserLoggedOut();

        getDataManager().wipeUserData()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        getMvpView().hideLoading();
                        getMvpView().showMessage(R.string.logging_you_out);
                        getMvpView().openLoginActivity();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideLoading();
                        getMvpView().showMessage(R.string.there_was_an_error_logout);
                    }
                });
    }

    @Override
    public void onViewInitialized() {

    }
}
