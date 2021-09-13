package ru.rinet.questik.ui.login.dialog;

import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpViewDialog;

@PerActivity
public interface AuthDialogFragmentMvpView extends MvpViewDialog {

    void openAuthDialog(String tag);

    void openMainActivity();

    void showAuthLoginView();

    void showAuthRegView();

    void dismissDialog();

}
