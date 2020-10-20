package ru.rinet.questik.ui.splash;


import android.os.Bundle;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.rinet.questik.R;
import ru.rinet.questik.ui.base.BaseActivity;
import ru.rinet.questik.ui.login.LoginActivity;
import ru.rinet.questik.ui.main.HomeActivity;


public class SplashActivity extends BaseActivity implements SplashMvpView {


    @Inject
    SplashMvpPresenter<SplashMvpView> presenter;

    @BindView(R.id.lottie_load)
    LottieAnimationView loadLAV;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        presenter.onAttach(SplashActivity.this);

        setUp();
    }

    @Override
    protected void onDestroy() {
        loadLAV.clearAnimation();
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        loadLAV.playAnimation();
    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(HomeActivity.getStartIntent(SplashActivity.this));
        finish();
    }
}
