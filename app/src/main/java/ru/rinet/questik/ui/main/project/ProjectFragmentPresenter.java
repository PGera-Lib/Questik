package ru.rinet.questik.ui.main.project;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.AppLogger;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class ProjectFragmentPresenter<V extends ProjectFragmentMvpView> extends BasePresenter<V>
        implements ProjectFragmentMvpPresenter<V>, ProjectFragmentAdapter.Callback {

    @Inject
    public ProjectFragmentPresenter(SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable,
                                    DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }


    @Override
    public void fetchProjectList() {
        showPersistentData();
/*        if (getMvpView().isNetworkConnected()) {
            getMvpView().showLoading();
            getCompositeDisposable().add(
                    getDataManager().getQuestik()
                            .flatMap(response -> Observable.concat(
                                    getDataManager().wipeProjectData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                    getDataManager().insertProjectList(response.getProject()).subscribeOn(getSchedulerProvider().io()))
                                    .doOnError(throwable -> AppLogger.e(ProjectFragmentPresenter.class.getSimpleName()))
                                    .ignoreElements()
                                    .andThen(Observable.fromArray(response.getProject())))
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(projects -> {
                                if (!isViewAttached())
                                    return;

                                getMvpView().hideLoading();

                                if (projects != null && projects.size() > 0)
                                    getMvpView().updateProjectList(projects);
                            }, throwable -> {
                                if (!isViewAttached())
                                    return;

                                getMvpView().hideLoading();
                                getMvpView().onError(R.string.could_not_fetch_items);
                                showPersistentData();
                            })
            );

        } else {
           showPersistentData();
        }*/
    }

    private void showPersistentData() {
        getCompositeDisposable().add(
                getDataManager().getProjectList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(projects -> {
                            if (!isViewAttached())
                                return;
                            if (projects != null) {
                                //getDataManager().getCorpById()
                                getMvpView().updateProjectList(projects);
                                getMvpView().onError(R.string.no_internet); 
                            }
                        }, throwable -> {
                            if (!isViewAttached())
                                return;

                            getMvpView().onError(R.string.could_not_show_items);
                        })
        );
    }


    @Override
    public void onProjectEmptyRetryClicked() {
        fetchProjectList();
    }
	
    @Override
    public void onProjectItemClicked(Project project) {
        getMvpView().openProjectDetailActivity(project);
    }


}