package ru.rinet.questik.ui.main.project.fragment.detail;

import ru.rinet.questik.ui.base.MvpPresenter;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectDetailFragmentMvpPresenter <V extends MvpView> extends MvpPresenter<V> {
    void onViewPrepared();
    void loadProjectData(Long s);
}
