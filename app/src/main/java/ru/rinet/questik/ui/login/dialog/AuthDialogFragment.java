package ru.rinet.questik.ui.login.dialog;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.FragmentManager;



import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.rinet.questik.R;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.ui.base.BaseDialog;
import ru.rinet.questik.ui.login.LoginActivity;
import ru.rinet.questik.ui.main.HomeActivity;
import ru.rinet.questik.ui.main.Interact;

public class AuthDialogFragment extends BaseDialog implements AuthDialogFragmentMvpView {


    private static final String TAG = "AuthDialog";

    public static int SETINT = 0;

    private Interact.AuthDialog callback;

    Context context;


    @Inject
    AuthDialogFragmentMvpPresenter<AuthDialogFragmentMvpView> mPresenter;

    /**
     * Regiset
     */
    @BindView(R.id.card_register)
    View regView;

    @BindView(R.id.et_name)
    EditText et_name;

    @BindView(R.id.et_newmail)
    EditText et_newmail;

    @BindView(R.id.et_newpass)
    EditText et_newpass;

    @BindView(R.id.btn_newuser)
    Button btn_newuser;

    @BindView(R.id.btn_cancel1)
    Button btn_cancel1;

    /**
     * Login
     */

    @BindView(R.id.card_login)
    View loginView;

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_password)
    EditText et_password;

    @BindView(R.id.btn_server_login)
    Button btn_server_login;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;



    public static AuthDialogFragment newInstance() {
        AuthDialogFragment fragment = new AuthDialogFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_auth, container, false);

        ActivityComponent component = getActivityComponent();
    //    if (component != null) {

            component.inject(this);


            setUnBinder(ButterKnife.bind(this, view));

            mPresenter.onAttach(this);
      //  }

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }

    @Override
    protected void setUp(View view) {

        SETINT = LoginActivity.AUTH;
       // callback.onAuthSwitch(SETINT);
       // onSwitch(SETINT);
        Log.i("AUTH_DIALOG", "SET INT -"+SETINT);
        switch (SETINT){
            case 1: SETINT=1;
            regView.setVisibility(View.GONE);
            loginView.setVisibility(View.VISIBLE);
                Log.i("AUTH_DIALOG", "LOGIN");
            break;
            case 2: SETINT=2;
            loginView.setVisibility(View.GONE);
            regView.setVisibility(View.VISIBLE);
                Log.i("AUTH_DIALOG", "REGISTER");
            break;
            case 0: SETINT=0;
                regView.setVisibility(View.GONE);
                loginView.setVisibility(View.GONE);
                Log.i("AUTH_DIALOG", "OFFLINE");
                break;
        }



    }

    @OnClick(R.id.btn_newuser)
    void onNewUserClicked() {
        context = getBaseActivity();
        mPresenter.onNewUserAdded(context, et_name.getText().toString(), et_newmail.getText().toString(), et_newpass.getText().toString());
    }

    @OnClick(R.id.btn_server_login)
    void onServerLoginClicked() {
        context = getBaseActivity();
        mPresenter.onUserLogined(context, et_email.getText().toString(), et_password.getText().toString());
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClicked() {
        dismiss();
       //mPresenter.onCancelClicked();
    }

    @OnClick(R.id.btn_cancel1)
    void onRegCancelClicked(){
        dismiss();
    }

    @Override
    public void openAuthDialog(String tag) {

    }

    @Override
    public void openMainActivity() {
        startActivity(HomeActivity.getStartIntent(getContext()));
        dismiss();
    }

    @Override
    public void showAuthLoginView() {

    }

    @Override
    public void showAuthRegView() {

    }

    @Override
    public void dismissDialog() {

    }

/*    @Override
    public void onSwitch(int n) {
        n=1;
        Log.i("LOGIN DIALOG", "SET INT -"+n);
        SETINT = n;
    }*/

}
















































