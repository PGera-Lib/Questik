package ru.rinet.questik.ui.main.catalog.activity;


import java.util.List;

import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.MvpView;

public interface CatalogDetailsMvpView extends MvpView {
    void onListRetrieved(List<Object> list);

    void openCatalogDetailsActivity(Job job);

//    void openOSDetailsActivity(OpenSource openSource);
}
