package ru.rinet.questik.ui.main;


import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;

/**
 * Created by Abhijit on 16-11-2017.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onNavMenuCreated();

    void onDrawerOptionHomeClicked();

    void onDrawerOptionProjectClicked();

    void onDrawerOptionCatalogClicked();

    void onDrawerOptionChernClicked();

    void onDrawerOptionDocClicked();

    void onDrawerOptionHowToClicked();

    void onDrawerOptionGameClicked();

    void onDrawerOptionChatClicked();

    void onDrawerOptionSettingsClicked();

    void onDrawerOptionLogoutClicked();

    void onViewInitialized();
}
