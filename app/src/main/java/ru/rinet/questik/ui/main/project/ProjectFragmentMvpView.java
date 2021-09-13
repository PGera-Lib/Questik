package ru.rinet.questik.ui.main.project;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectFragmentMvpView extends MvpView {

    void updateProjectList(List<Project> projectList);

    void openProjectDetailActivity(Project project);

}