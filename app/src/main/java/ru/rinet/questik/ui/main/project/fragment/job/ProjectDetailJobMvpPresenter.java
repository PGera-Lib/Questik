package ru.rinet.questik.ui.main.project.fragment.job;

import ru.rinet.questik.ui.base.MvpPresenter;

public interface ProjectDetailJobMvpPresenter <V extends ProjectDetailJobMvpView> extends MvpPresenter<V> {
   void fetchJobList();
}
