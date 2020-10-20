package ru.rinet.questik.ui.main.catalog.fragment;


import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;

@PerActivity
public interface CatalogFragmentMvpPresenter<V extends CatalogFragmentMvpView> extends MvpPresenter<V> {

    void fetchJobList();

}