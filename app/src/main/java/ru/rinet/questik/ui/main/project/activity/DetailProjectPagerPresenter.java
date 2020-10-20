package ru.rinet.questik.ui.main.project.activity;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.ui.base.MvpView;
import ru.rinet.questik.ui.main.project.ProjectFragmentPresenter;
import ru.rinet.questik.utils.AppLogger;
import ru.rinet.questik.utils.rx.SchedulerProvider;

import static io.reactivex.schedulers.Schedulers.start;

public class DetailProjectPagerPresenter <V extends MvpView> extends BasePresenter<V> implements
        DetailProjectPagerMvpPresenter<V> {
    private static final String TAG = "DetailProjectPagerPresenter";
    @Inject
    public DetailProjectPagerPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }

    @Override
    public void onSaveButtonClicked(Project project, Corp corp, User user, List<Job> joblist) {
        if (project != null) {
            Log.i("OnSaveButtonClicked", "разбираем что собрали"+"проект: "+project.getName()+" Corp: "+ corp.getId()+" user: "+ user.getId());
            AsyncTask.execute(() ->getDataManager().insertFullProject(project, corp, user, joblist));
          getMvpView().showMessage("заебись!!!");
        } else {
            getMvpView().onError(R.string.project_save_error);
        }
    }
}
