package ru.rinet.questik.ui.login;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.rinet.questik.R;
import ru.rinet.questik.ui.base.BaseActivity;
import ru.rinet.questik.ui.login.dialog.AuthDialogFragment;
import ru.rinet.questik.ui.main.HomeActivity;
import ru.rinet.questik.ui.main.Interact;


public class LoginActivity extends BaseActivity implements LoginMvpView, Interact.AuthDialog
{

    private static final String EMAIL = "email";

    public static final int LOGIN = 1;
    public static final int REG = 2;
    public static int AUTH;

    @Inject
    FirebaseAuth mAuth;

  //  private Interactor.AuthDialogCallback dialogCallback;
/*
    @Inject
    FirebaseAuth mAuth;


    @Inject
    FirebaseUser user;*/
/*    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;*/

    @BindView(R.id.btn_server_login)
    Button btn_login;

    @BindView(R.id.btn_reg)
    Button btn_reg;

    @BindView(R.id.btn_offline)
    Button btn_offline;

    @Inject
    LoginMvpPresenter<LoginMvpView> presenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(LoginActivity.this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        setUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {
            openMainActivity();
        }

    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClicked() {
        presenter.onServerLoginClicked();
        AuthDialogFragment doDialog = AuthDialogFragment.newInstance();
        FragmentManager  fm = getSupportFragmentManager();
        doDialog.show(fm,"Login");
        onAuthSwitch(LOGIN);
        AUTH=LOGIN;
        Log.i("LOGIN ACTIVITY", "LOGIN SET INT -"+LOGIN );
//        dialogCallback.onSwitch(LOGIN);

    }

    @OnClick(R.id.btn_reg)
    void onServerRegClicked() {
        AuthDialogFragment doDialog = AuthDialogFragment.newInstance();
        FragmentManager  fm = getSupportFragmentManager();
        doDialog.show(fm,"Reg");
        Log.i("LOGIN ACTIVITY", "REG SET INT -"+REG );
        onAuthSwitch(REG);
        AUTH=REG;
//        dialogCallback.onSwitch(REG);
    }



    @OnClick(R.id.btn_offline)
    void onOfflineLoginClicked() {
    openMainActivity();
    Log.i("LoginActivity", "Типо должно переходить на хоумактивитит");}



    @Override
    protected void setUp() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void openMainActivity() {
        startActivity(HomeActivity.getStartIntent(this));
        finish();
    }

    @Override
    public void onAuthSwitch(int n) {
     //   Log.i("LOGIN ACTIVITY", "onAuthSwitch -"+n );
     //   n=1;
        n=AUTH;
        Log.i("LOGIN ACTIVITY", "onAuthSwitch -"+n );
    }

}
