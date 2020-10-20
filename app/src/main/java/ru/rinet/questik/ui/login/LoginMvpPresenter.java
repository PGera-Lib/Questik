package ru.rinet.questik.ui.login;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;

/**
 * Created by Abhijit on 09-11-2017.
 */

@PerActivity
public interface LoginMvpPresenter<V extends LoginMvpView> extends MvpPresenter<V> {

    void onServerLoginClicked();

    void onOfflineLoginClicked();

/*    void onGoogleLoginClicked();

    void onGoogleSignInResult(Task<GoogleSignInAccount> task);*/

/*    void onFacebookSignInResult(AccessToken accessToken, Profile currentProfile);*/
}
