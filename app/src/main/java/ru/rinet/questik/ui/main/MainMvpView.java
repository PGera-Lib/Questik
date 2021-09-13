package ru.rinet.questik.ui.main;


import ru.rinet.questik.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void updateAppVersion();

    void updateUserName(String name);

    void updateUserEmail(String email);

    void updateUserProfilePicture(String picPath);

   /* void openHomeFragment();
*/
    void openLoginActivity();
/*
    void openProjectFragment();

    void openCatalogFragment();

    void openChernFragment();

    void openSettingsFragment();

    void openChatFragment();

    void openGameFragment();

    void openDocFragment();

    void openHowtoFragment();

    void openProjectDetailActivity();

    void openCatalogDetailAtivity();*/

    void closeNavigationDrawer();

    void unlockDrawer();

    void lockDrawer();
}
