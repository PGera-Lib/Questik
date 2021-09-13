package ru.rinet.questik.ui.login.dialog;


import android.content.Context;

import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.MvpPresenter;

public interface AuthDialogFragmentMvpPresenter<V extends AuthDialogFragmentMvpView> extends MvpPresenter<V> {

    void onNewUserAdded(Context context, String name, String mail, String password);

    void onUserLogined(Context context, String mail, String password);

    void onCancelClicked();

}
