package ru.rinet.questik.ui.main.project.fragment.job;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class ProjectDetailJobPresenter <V extends ProjectDetailJobMvpView> extends BasePresenter<V>
        implements ProjectDetailJobMvpPresenter<V>, ProjectDetailJobAdapter.Callback {
    @Inject
    public ProjectDetailJobPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
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
