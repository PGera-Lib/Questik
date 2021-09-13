package ru.rinet.questik.ui.login;

import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.retrofit.pojo.LoginRequest;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.CommonUtils;
import ru.rinet.questik.utils.rx.SchedulerProvider;
import timber.log.Timber;

/**
 * Created by Abhijit on 09-11-2017.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    LoginPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);
    }


    /*
    *
    * Login from Google and Facebook has negative id's for identification, Server has positive ID's
    *
    * Based on the ID's, we can deduce the form of Login
    * */
    @Override
    public void onServerLoginClicked() {
/*
        if (getMvpView().isNetworkConnected()) {
            if (email == null || email.isEmpty()) {
                getMvpView().onError(R.string.empty_email);
                return;
            }
            if (!CommonUtils.isEmailValid(email)) {
                getMvpView().onError(R.string.invalid_email);
                return;
            }
            if (password == null || password.isEmpty()) {
                getMvpView().onError(R.string.empty_password);
                return;
            }
*/

/*            getMvpView().showLoading();
            getCompositeDisposable().add(
                    getDataManager().getUserRequest(new LoginRequest.ServerLoginRequest(email, password))
                            .concatMap(user -> getDataManager().saveUser(user)
                                    .ignoreElements()
                                    .andThen(Observable.just(user)))
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(user -> {
                                getDataManager().updateUserInfoInPrefs(user.getId(),
                                        user.getName(),
                                        user.getMail(),
                                        DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_SERVER);

                                if (!isViewAttached())
                                    return;

                                getMvpView().hideLoading();
                                getMvpView().showMessage(R.string.signing_in);
                                getMvpView().openMainActivity();
                            }, throwable -> {
                                if (!isViewAttached())
                                    return;

                                getMvpView().hideLoading();
                                getMvpView().onError(R.string.server_sign_in_failed);
                            })
            );*/


    }

    @Override
    public void onOfflineLoginClicked() {
        getMvpView().openMainActivity();
    }


    private void insertCurrentUserIntoDb(User user) {

                getDataManager().saveUser(user);

    }
}
