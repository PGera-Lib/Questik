package ru.rinet.questik.ui.main.project.fragment.itog;

import ru.rinet.questik.ui.base.MvpPresenter;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectItogMvpPresenter<V extends MvpView> extends MvpPresenter<V> {
    void onViewPrepared();
}
