package ru.rinet.questik.ui.main.project.fragment.job;

import java.util.List;

import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectDetailJobMvpView extends MvpView {
    void updateProjectJobList(List<Job> list);

    void openJobDetailActivity(Job job);

    void onProjectJobListReFetched();

    void onPullToRefreshEvent(boolean isVisible);
}
