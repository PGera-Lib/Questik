package ru.rinet.questik.ui.base;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import butterknife.Unbinder;
import ru.rinet.questik.App;
import ru.rinet.questik.R;
import ru.rinet.questik.di.components.ActivityComponent;
import ru.rinet.questik.di.components.DaggerActivityComponent;
import ru.rinet.questik.di.module.ActivityModule;
import ru.rinet.questik.di.module.FirebaseModule;
import ru.rinet.questik.utils.CommonUtils;
import ru.rinet.questik.utils.NetworkUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback {

    private ProgressDialog progressDialog;
    private Unbinder unBinder;
    private ActivityComponent activityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instantiate ActivityComponent
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .firebaseModule(new FirebaseModule())
                .applicationComponent(((App) getApplication()).getApplicationComponent())
                .build();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.cancel();
    }

    @Override
    public void showLoading() {
        hideLoading();
        progressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void showMessage(String message) {
        if (message != null)
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, R.string.some_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int resID) {
        showMessage(getString(resID));
    }

    @Override
    public void onError(String message) {
        if (message != null)
            showSnackbar(message);
        else showSnackbar(getString(R.string.some_error));
    }

    @Override
    public void onError(int resID) {
        onError(getString(resID));
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (NetworkUtils.isNetworkConnected(getApplicationContext()))

            return true;
        else {
            onError(R.string.no_internet);
            return false;
        }
    }

    private void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_SHORT);

        View view = snackbar.getView();

        TextView snackTV = (TextView) view.findViewById(R.id.snackbar_text);
        snackTV.setTextColor(ContextCompat.getColor(this, R.color.white));

        snackbar.show();
    }

    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    public void setUnBinder(Unbinder unBinder) {
        this.unBinder = unBinder;
    }

    @Override
    protected void onDestroy() {
        if (unBinder != null)
            unBinder.unbind();
        super.onDestroy();
    }

    protected abstract void setUp();

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }
}
