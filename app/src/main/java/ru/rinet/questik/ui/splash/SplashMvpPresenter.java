package ru.rinet.questik.ui.splash;


import ru.rinet.questik.di.scope.PerActivity;
import ru.rinet.questik.ui.base.MvpPresenter;


@PerActivity
public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
}
