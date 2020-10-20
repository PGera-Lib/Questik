package ru.rinet.questik.ui.main.project.activity;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;

public interface onDetailActivityDataListener {
    void onDetailActivityDataListener();
    void onDetailActivityDataReciver(Project project, Corp corp, User user, List<Job> jobList);
}
