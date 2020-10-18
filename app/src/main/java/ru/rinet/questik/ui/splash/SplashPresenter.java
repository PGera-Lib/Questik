package ru.rinet.questik.ui.splash;



import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;


public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {

    @Inject
    SplashPresenter(SchedulerProvider schedulerProvider,
                    CompositeDisposable compositeDisposable,
                    DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        startActivityWithDelay();
    }

    private void startActivityWithDelay() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                decideNextActivity();
            }
        }, 2000);
    }

    private void decideNextActivity() {
        if (getDataManager().getCurrentUserLoggedInMode() ==
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType())
            getMvpView().openLoginActivity();
        else getMvpView().openMainActivity();
    }
}
