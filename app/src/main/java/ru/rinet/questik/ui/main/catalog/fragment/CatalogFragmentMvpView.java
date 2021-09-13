package ru.rinet.questik.ui.main.catalog.fragment;


import java.util.List;

import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.MvpView;

public interface CatalogFragmentMvpView extends MvpView {

    void updateCatalogList(List<Job> JobList);

    void openCatalogDetailFragment(Job job);

    void onCatalogListReFetched();
}