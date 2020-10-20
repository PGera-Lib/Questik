package ru.rinet.questik.ui.main.project.fragment.itog;

import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectItogMvpView extends MvpView {
    void getProject(Project project);
}
