package ru.rinet.questik.ui.main.project.fragment;

import java.util.List;

import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;

public interface Interacts {

/*interface onDetailActivityDataListener {
void onDetailActivityDataListener();
}*/

interface onDetailFragmentDataListener {
void onDetailFragmentDataListener(Project project, Corp corp, User user);
void onDetailFragmentDataReciver();
}

interface onDetailJobDataListener {
void onDetailJobDataListener(List<Job> joblist);
}
interface onDetailMaterialDataListener {
void onDetailMaterialDataListener(List<Job> jobList);
}
}
