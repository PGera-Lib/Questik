package ru.rinet.questik.ui.main.project.fragment.material;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.ui.base.MvpView;
import ru.rinet.questik.ui.main.project.fragment.detail.ProjectDetailFragmentMvpPresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class ProjectDetailMaterialPresenter <V extends MvpView> extends BasePresenter<V> implements
        ProjectDetailMaterialMvpPresenter<V>, ProjectDetailMaterialAdapter.Callback {
    @Inject
    public ProjectDetailMaterialPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onViewPrepared() {
    }

    @Override
    public void onJobEmptyRetryClicked() {

    }

    @Override
    public void onJobItemClicked(Job job) {

    }
    @Override
    public void fetchJobList() {
/**
 * ToDo: вкорячить метод получения списка работ и добавление его в Лист: из сети и из базы
 */
    }
}