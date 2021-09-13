package ru.rinet.questik.ui.main.project.fragment.detail;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.ui.base.MvpView;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class ProjectDetailFragmentPresenter <V extends MvpView> extends BasePresenter<V> implements
        ProjectDetailFragmentMvpPresenter<V> {

    private static final String TAG = "ProjectDetailFragment";


    @Inject
    public ProjectDetailFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onViewPrepared() {

    }

    @Override
    public void loadProjectData(Long s) {
        getCompositeDisposable().add(
                (Disposable) getDataManager().getCurrentProject(s)
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui()));
    }
}
