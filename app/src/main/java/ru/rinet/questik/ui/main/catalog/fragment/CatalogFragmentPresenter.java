package ru.rinet.questik.ui.main.catalog.fragment;

import android.util.Log;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import ru.rinet.questik.data.DataManager;

import ru.rinet.questik.data.retrofit.mapper.JobMapper;

import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.AppLogger;
import ru.rinet.questik.utils.rx.SchedulerProvider;
import ru.rinet.questik.R;



public class CatalogFragmentPresenter<V extends CatalogFragmentMvpView> extends BasePresenter<V>
        implements CatalogFragmentMvpPresenter<V>, CatalogFragmentAdapter.Callback {

    @Inject
    public CatalogFragmentPresenter(SchedulerProvider schedulerProvider,
                                    CompositeDisposable compositeDisposable,
                                    DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }


    @Override
    public void fetchJobList() {
        if (getMvpView().isNetworkConnected()) {
            //getMvpView().showLoading();

            /**
             * getCompositeDisposable - утилизатор потоков, в моем случае RX Java
             * getDataManager().doJobListApiCall() - добавляем в поток получение данных через API
             * .flatMap(jobList -> Observable.concat( --- ожидание подписки на каждую наблюдаемую информацию (
             * getDataManager().wipeJobData() - первоначальная очистка прошлых данных
             * getDataManager().insertJobList(jobList) - добавление данных в БД ROOM
             * subscribeOn(getSchedulerProvider().io()) - оператор определяющий в каком Scheduler (поток) будет выполняться процесс
             *
             *.doOnError(throwable -> AppLogger.e(throwable, CatalogFragmentPresenter.class.getSimpleName()))
             * .ignoreElements() --- получать только уведомления о заверешении или ошибке, приприменении к Observable гарантирует что он никогда не вызовет его.
             */

/*            getCompositeDisposable().add(
                            getDataManager().getQuestik()
                            .flatMap(response -> Observable.concat(
                                    getDataManager().wipeJobData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                    getDataManager().insertJobList(response.getJobs()).subscribeOn(getSchedulerProvider().io()))
                                    .doOnError(throwable -> AppLogger.e(CatalogFragmentPresenter.class.getSimpleName()))
                                    .ignoreElements()
                                    .andThen(Observable.fromArray(response.getJobs())))
                            .subscribeOn(getSchedulerProvider().io())
                            .observeOn(getSchedulerProvider().ui())
                            .subscribe(jobList -> {
                                if (!isViewAttached())
                                    return;
                                getMvpView().hideLoading();

                                if (jobList != null && jobList.size() > 0)
                                    getMvpView().updateCatalogList(jobList);
                                //loadAll();
                            }, throwable -> {
                                if (!isViewAttached())
                                    return;

                                getMvpView().hideLoading();
                                getMvpView().onError(R.string.could_not_fetch_items);
                                showPersistentData();
                            })
            );

        } else {*/
           showPersistentData();
        }
    }

    private void loadAll(){
        getCompositeDisposable().add(
                getDataManager().getQuestik()
                        .flatMap(response -> Observable.concat(
                                getDataManager().wipeProjectData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                getDataManager().insertProjectList(response.getProject()).subscribeOn(getSchedulerProvider().io()))
                                .doOnError(throwable -> AppLogger.e(CatalogFragmentPresenter.class.getSimpleName()))
                                .ignoreElements()
                                .andThen(Observable.fromArray(response.getProject())))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(projects -> {
                            List<Corp> projectCorps = new ArrayList<>();
                            List<Job> projectJob = new ArrayList<>();
                            List<User> projectUser = new ArrayList<>();
                            if (projects != null && projects.size() > 0) {
                                for (int i = 0; i < projects.size(); i++) {
                                   /* Corp corps = projects.get(i).getCorp();
                                        String a, b, c;
                                        a=corps.getId().toString();
                                        b=corps.getName().toString();
                                        c=corps.getPhone().toString();
                                        Log.i("COOOOORP", "CORP in PROJECT: " + a+" "+b+""+c);*/
                                    }
                            }


                            Log.i("Fragment Presenter", "Project List is: "+projects);
                        })
        );

        getCompositeDisposable().add(
                getDataManager().getQuestik()
                        .flatMap(response -> Observable.concat(
                                getDataManager().wipeDepartamentData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                getDataManager().insertDepartamentList(response.getDepartament()).subscribeOn(getSchedulerProvider().io()))
                                .doOnError(throwable -> AppLogger.e(CatalogFragmentPresenter.class.getSimpleName()))
                                .ignoreElements()
                                .andThen(Observable.fromArray(response.getDepartament())))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(departaments -> {
                            Log.i("Fragment Presenter", "Departments List is: "+departaments);
                        })
        );

        getCompositeDisposable().add(
                getDataManager().getQuestik()
                        .flatMap(response -> Observable.concat(
                                getDataManager().wipeUserData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                getDataManager().insertUserList(response.getUser()).subscribeOn(getSchedulerProvider().io()))
                                .doOnError(throwable -> AppLogger.e(CatalogFragmentPresenter.class.getSimpleName()))
                                .ignoreElements()
                                .andThen(Observable.fromArray(response.getUser())))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(users -> {
                            Log.i("Fragment Presenter", "Users List is: "+users);
                        })
        );

        getCompositeDisposable().add(
                getDataManager().getQuestik()
                        .flatMap(response -> Observable.concat(
                                getDataManager().wipeCorpData().subscribeOn(getSchedulerProvider().io()).toObservable(),
                                getDataManager().insertCorpList(response.getCorp()).subscribeOn(getSchedulerProvider().io()))
                                .doOnError(throwable -> AppLogger.e(CatalogFragmentPresenter.class.getSimpleName()))
                                .ignoreElements()
                                .andThen(Observable.fromArray(response.getCorp())))
                        .subscribeOn(getSchedulerProvider().io())
                        .observeOn(getSchedulerProvider().ui())
                        .subscribe(corps -> {
                            Log.i("Fragment Presenter", "Corp List is: "+corps);
                        })
        );


    }

    private void showPersistentData() {
        getCompositeDisposable().add(
                getDataManager().getJobList()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(jobList -> {
                            Log.i("Fragment Presenter", jobList + " 22");
                            if (!isViewAttached())
                                return;

                            if (jobList != null) {
                                getMvpView().updateCatalogList(jobList);
                                getMvpView().onError(R.string.no_internet);
                                Log.i("Fragment Presenter", jobList + " 23");
                            }
                        }, throwable -> {
                            if (!isViewAttached())
                                return;

                            getMvpView().onError(R.string.could_not_show_items);
                            getCompositeDisposable().clear();
                            Log.i("Fragment Presenter", R.string.could_not_show_items + " 12");
                        })
        );
    }


    @Override
    public void onJobEmptyRetryClicked() {
        fetchJobList();
        getMvpView().onCatalogListReFetched();
    }

    @Override
    public void onJobItemClicked(Job job) {
        getMvpView().openCatalogDetailFragment(job);
    }


}