package ru.rinet.questik.ui.main.project.fragment.material;

import ru.rinet.questik.ui.base.MvpPresenter;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectDetailMaterialMvpPresenter <V extends MvpView> extends MvpPresenter<V> {
    void onViewPrepared();
    void fetchJobList();
}
