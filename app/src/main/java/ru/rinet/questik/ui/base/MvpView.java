package ru.rinet.questik.ui.base;


import androidx.annotation.StringRes;


public interface MvpView {

    void hideLoading();

    void showLoading();

    void showMessage(String message);

    void showMessage(@StringRes int resID);

    void onError(String message);

    void onError(@StringRes int resID);

    void hideKeyboard();

    boolean isNetworkConnected();
}
