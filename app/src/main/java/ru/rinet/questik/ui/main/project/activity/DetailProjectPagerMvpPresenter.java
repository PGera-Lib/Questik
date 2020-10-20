package ru.rinet.questik.ui.main.project.activity;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.MvpPresenter;
import ru.rinet.questik.ui.base.MvpView;

public interface DetailProjectPagerMvpPresenter <V extends MvpView> extends MvpPresenter<V> {
    void onSaveButtonClicked(Project project, Corp corp, User user, List<Job> jobList);
}
