package ru.rinet.questik.ui.main.catalog.activity;

import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;

@PerActivity
public interface CatalogDetailsMvpPresenter<V extends CatalogDetailsMvpView> extends MvpPresenter<V> {
    void onViewPrepared();


}