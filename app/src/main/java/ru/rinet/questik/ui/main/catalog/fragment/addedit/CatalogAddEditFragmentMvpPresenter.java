package ru.rinet.questik.ui.main.catalog.fragment.addedit;

import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.MvpPresenter;

public interface CatalogAddEditFragmentMvpPresenter <V extends CatalogAddEditFragmentMvpView> extends MvpPresenter<V> {

    void onCatalogItemSaved(Job job);

    void onCatalogItemAdd(Job job);

    void onCancelClicked();

}
