package ru.rinet.questik.ui.login.dialog;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.ui.login.LoginActivity;
import ru.rinet.questik.ui.login.LoginMvpView;
import ru.rinet.questik.utils.CommonUtils;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class AuthDialogFragmentPresenter<V extends AuthDialogFragmentMvpView> extends BasePresenter<V>
        implements AuthDialogFragmentMvpPresenter<V> {

    private static final String TAG = "AuthDialogFragmentPresenter";

    @Inject
    FirebaseAuth mAuth;


/*    @Inject
    FirebaseUser user;*/




    @Inject
    public AuthDialogFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }


    @Nullable
    @Override
    public void onNewUserAdded(Context context, String name, String mail, String password) {

        Log.i("AUTH PRESENTER",  "register new user by:"+name+" "+mail+" "+password);
        if (getMvpView().isNetworkConnected()) {
            if (name == null || name.isEmpty()) {
                getMvpView().onError(R.string.empty_name);
                return;
            }
            if (mail == null || mail.isEmpty()) {
                getMvpView().onError(R.string.empty_email);
                return;
            }
            if (!CommonUtils.isEmailValid(mail)) {
                getMvpView().onError(R.string.invalid_email);
                return;
            }
            if (password == null || password.isEmpty()) {
                getMvpView().onError(R.string.empty_password);
                return;
            }

            getMvpView().showLoading();
            /*getDataManager().registerUserFireBase(name, mail, password);*/
            mAuth.createUserWithEmailAndPassword(mail, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name+"")
                                        .build();
                                Log.i("AUTH PRESENTER",  "profile user by:"+name+" "+mail+" "+password);
                                user.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile updated.");
                                                    getMvpView().openMainActivity();
                                                }
                                            }
                                        });

                                updateUI(user);

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                getMvpView().onError(R.string.invalid_auth);
                                updateUI(null);
                            }


                        }
                    });

            getMvpView().hideLoading();
        }

    }
@Nullable
    public void updateUI (FirebaseUser user) {
        if (user != null) {
            // Name, email address, and profile photo Url
            String fname = user.getDisplayName();
            String fmail = user.getEmail();
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();
            getMvpView().showMessage(R.string.signing_in);

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        } else {
            getMvpView().showMessage(R.string.register_error);
        }
    }
    @Nullable
    @Override
    public void onUserLogined(Context context, String mail, String password) {
        if (getMvpView().isNetworkConnected()) {
            if (mail == null || mail.isEmpty()) {
                getMvpView().onError(R.string.empty_email);
                return;
            }
            if (!CommonUtils.isEmailValid(mail)) {
                getMvpView().onError(R.string.invalid_email);
                return;
            }
            if (password == null || password.isEmpty()) {
                getMvpView().onError(R.string.empty_password);
                return;
            }

            getMvpView().showLoading();
           /* getDataManager().loginUserFireBase(mail, password);*/

            getMvpView().hideLoading();
            mAuth.signInWithEmailAndPassword(mail, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                getMvpView().openMainActivity();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                getMvpView().onError(R.string.invalid_auth);
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    @Override
    public void onCancelClicked() {
    }
}
