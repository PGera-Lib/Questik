package ru.rinet.questik.ui.main.project;

import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;

@PerActivity
public interface ProjectFragmentMvpPresenter<V extends ProjectFragmentMvpView> extends MvpPresenter<V> {

    void fetchProjectList();


}