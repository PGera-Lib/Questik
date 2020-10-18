package ru.rinet.questik.di.components;


import dagger.Component;
import ru.rinet.questik.di.module.FirebaseModule;
import ru.rinet.questik.ui.login.dialog.AuthDialogFragment;
import ru.rinet.questik.ui.main.HomeActivity;
import ru.rinet.questik.di.module.ActivityModule;
import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.login.LoginActivity;
import ru.rinet.questik.ui.main.catalog.activity.CatalogDetailsActivity;
import ru.rinet.questik.ui.main.catalog.fragment.CatalogFragment;
import ru.rinet.questik.ui.main.catalog.fragment.addedit.CatalogAddEditFragment;
import ru.rinet.questik.ui.main.chat.ChatFragment;
import ru.rinet.questik.ui.main.chern.ChernFragment;
import ru.rinet.questik.ui.main.help.doc.FragmentDoc;
import ru.rinet.questik.ui.main.help.howto.FragmentHowto;
import ru.rinet.questik.ui.main.help.GameFragment;
import ru.rinet.questik.ui.main.home.HomeFragment;
import ru.rinet.questik.ui.main.project.ProjectFragment;
import ru.rinet.questik.ui.main.project.activity.DetailProjectPagerActivity;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragment;
import ru.rinet.questik.ui.main.project.fragment.job.ProjectDetailJobFragment;
import ru.rinet.questik.ui.main.project.fragment.material.ProjectDetailMaterialFragment;
import ru.rinet.questik.ui.main.project.fragment.itog.ProjectItogFragment;
import ru.rinet.questik.ui.main.settings.SettingsFragment;
import ru.rinet.questik.ui.splash.SplashActivity;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FirebaseModule.class})

public interface ActivityComponent {

    /**
    Главные активити
     */

    void inject(SplashActivity activity);

    void inject(LoginActivity activity);

    void inject(HomeActivity activity);




    /**
     фрагменты
     */

    void inject(CatalogFragment fragment);

   // void inject(ChatFragment fragment);

   // void inject(ChernFragment fragment);

/*
    void inject(FragmentDoc fragment);

    void inject(FragmentHowto fragment);

    void inject(GameFragment fragment);

    void inject(HomeFragment fragment);

    void inject(SettingsFragment fragment);
*/

    /**
     * Project Activity
     * @param activity
     */
    void inject(DetailProjectPagerActivity activity);

    /**
     * Project Fragments
     * @param fragment
     */

    void inject(ProjectFragment fragment);
    void inject(ProjectDetailFragment fragment);
    void inject(ProjectDetailJobFragment fragment);
    void inject(ProjectDetailMaterialFragment fragment);
    void inject(ProjectItogFragment fragment);


    /**
     посредственные активити
     */

    //void inject(CatalogDetailsActivity activity);

    //void inject(ChernDetailsActivity activity);

    //void inject(DocDetailsActivity activity);

    //void inject(GameDetailsActivity activity);

    //void inject(ProjectDetailsActivity activity);


    /**
     * Dialog
     */
    void inject(CatalogAddEditFragment dialog);

    void inject(AuthDialogFragment authDialog);
}
