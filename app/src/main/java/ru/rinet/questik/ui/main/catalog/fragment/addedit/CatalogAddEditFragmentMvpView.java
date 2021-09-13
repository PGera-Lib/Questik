package ru.rinet.questik.ui.main.catalog.fragment.addedit;

import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpViewDialog;

@PerActivity
public interface CatalogAddEditFragmentMvpView extends MvpViewDialog {

    void openCatalogFragment();

    void showCatalogFragmentView();

    void showCatalogFragmentMessageView();

    void hideSubmitButton();

    void dismissDialog();

}
