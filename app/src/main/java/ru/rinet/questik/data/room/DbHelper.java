package ru.rinet.questik.data.room;



import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Category;
import ru.rinet.questik.data.room.models.CategoryWithJob;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.ProjectWithJobs;
import ru.rinet.questik.data.room.models.User;



public interface DbHelper {


    //Category

    Observable<Long> saveCategory(Category category);

    Observable<Integer> editCategory(Category category);

    Observable<Integer> removeCategory(Category category);

    Observable<Category> getCurrentCategory();

    Observable<List<Long>> insertCategoryList(List<Category> categoryList);

    Observable<List<CategoryWithJob>> getCategoryWithJob();

    Completable wipeCategoryData();

    //Corp

    public long saveCorp(Corp corp);

    public long getCorpById(long id);

    Observable<Integer> editCorp(Corp corp);

    Observable<Integer> removeCorp(Corp corp);

    Observable<List<Long>> insertCorpList(List<Corp> corpList);

    Observable<Corp> getCurrentCorp();

    Completable wipeCorpData();

    //Departament

    Observable<Long> saveDepartament(Departament departament);

    Observable<Integer> editDepartament(Departament departament);

    Observable<Integer> removeDepartament(Departament departament);

    Observable<List<Long>> insertDepartamentList(List<Departament> departamentList);

    Observable<Departament> getCurrentDepartament();

    Completable wipeDepartamentData();


    //Job
    Observable<Long> insertJob(Job job);

    Observable<Integer> editJob(Job job);

    Observable<Integer> removeJob(Job job);

    Observable<List<Long>> insertJobList(List<Job> jobList);

    Observable<List<Job>> getJobList();

    Observable<Long> getJobRecordCount();

    Completable wipeJobData();

    //Observable<List<CategoryWithJob>> getJobWithCategory(Long id);


    //Project
   public long insertProject(Project project);

    void insertFullProject(Project project, Corp corp, User user, List<Job> joblist);

    Observable<Integer> editProject(Project project);

    Observable<Integer> removeProject(Project project);

    Observable<List<Project>> getProjectList();

    Observable<List<Long>> insertProjectList(List<Project> projectList);

    Observable<List<ProjectWithJobs>> getProjectWithJobs();

    Observable<Long> getProjectRecordCount();

    Observable<Project> getCurrentProject(long id);

    Completable wipeProjectData();




    //Observable<ProjectWithJobs> getJobWithProject(Long id);

    //User

    public long saveUser(User user);

    Observable<Integer> editUser(User user);

    Observable<Integer> removeUser(User user);

    Observable<List<Long>> insertUserList(List<User> userList);

    Observable<User> getCurrentUser();

    Completable wipeUserData();

    //QUESTIK
    Observable<Long> saveQuestik(QuestikResponse questikResponse);

    Observable<Integer> editQuestik(QuestikResponse questikResponse);

    Observable<Integer> removeQuestik(QuestikResponse questikResponse);

    Observable<QuestikResponse> getCurrentQuestik();

}
