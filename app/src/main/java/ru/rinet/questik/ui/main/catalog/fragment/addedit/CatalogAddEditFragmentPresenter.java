package ru.rinet.questik.ui.main.catalog.fragment.addedit;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import ru.rinet.questik.R;
import ru.rinet.questik.data.DataManager;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.BasePresenter;
import ru.rinet.questik.utils.rx.SchedulerProvider;

public class CatalogAddEditFragmentPresenter <V extends CatalogAddEditFragmentMvpView> extends BasePresenter<V>
        implements CatalogAddEditFragmentMvpPresenter<V> {

    @Inject
    public CatalogAddEditFragmentPresenter(SchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable, DataManager dataManager) {
        super(schedulerProvider, compositeDisposable, dataManager);
    }


    @Override
    public void onCatalogItemSaved(Job job) {
        if (job != null) {
            getDataManager().insertJob(job);
            getMvpView().showMessage(R.string.job_saved);
            return;
        }

    }

    @Override
    public void onCatalogItemAdd(Job job) {

    }

    @Override
    public void onCancelClicked() {

    }
}
