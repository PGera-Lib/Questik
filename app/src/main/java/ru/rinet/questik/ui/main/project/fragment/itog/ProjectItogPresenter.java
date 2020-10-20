package ru.rinet.questik.ui.main.project.fragment.itog;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.ui.base.MvpView;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragmentMvpPresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class ProjectItogPresenter<V extends MvpView> extends BasePresenter<V> implements
        ProjectItogMvpPresenter<V> {

    @Inject
    public ProjectItogPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onViewPrepared() {

    }
}
