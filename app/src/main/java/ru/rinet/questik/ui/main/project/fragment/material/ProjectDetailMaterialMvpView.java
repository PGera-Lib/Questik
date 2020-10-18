package ru.rinet.questik.ui.main.project.fragment.material;

import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.ui.base.MvpView;

public interface ProjectDetailMaterialMvpView extends MvpView {
    void getMaterial(Job job);
}
