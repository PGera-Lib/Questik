package ru.rinet.questik.data.room;


import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Category;
import ru.rinet.questik.data.room.models.CategoryWithJob;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.ProjectWithJobs;
import ru.rinet.questik.data.room.models.User;

/**
 * Created by Abhijit on 11-11-2017.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private AppDatabase appDatabase;

    @Inject
    AppDbHelper(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    /**
     *Category
     */
    @Override
    public Observable<Long> saveCategory(Category category) {
        return Observable.fromCallable((Callable<Long>) () -> appDatabase.getCategoryDao().insert(category));
    }

    @Override
    public Observable<Integer> editCategory(Category category) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getCategoryDao().update(category));
    }

    @Override
    public Observable<Integer> removeCategory(Category category) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getCategoryDao().delete(category));
    }

    @Override
    public Observable<Category> getCurrentCategory() {
        return Observable.fromCallable(() -> appDatabase.getCategoryDao().getCategory());
    }

    @Override
    public Observable<List<Long>> insertCategoryList(List<Category> categoryList) {
        return Observable.fromCallable(() -> appDatabase.getCategoryDao().insertCategoryList(categoryList));
    }

    @Override
    public Observable<List<CategoryWithJob>> getCategoryWithJob() {
        return Observable.fromCallable(() -> appDatabase.getCategoryDao().getCategoryWithJob());
    }


    @Override
    public Completable wipeCategoryData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getCategoryDao().wipeCategoryTable();
            }
        });
    }

    /**
     *Corp
     */

    @Override
    public long saveCorp(Corp corp) {
        return appDatabase.getCorpDao().insert(corp);
    }

    @Override
    public long getCorpById(long id) {
        return appDatabase.getCorpDao().getById(id);
    }


    @Override
    public Observable<Integer> editCorp(Corp corp) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getCorpDao().update(corp));
    }

    @Override
    public Observable<Integer> removeCorp(Corp corp) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getCorpDao().delete(corp));
    }

    @Override
    public Observable<List<Long>> insertCorpList(List<Corp> corpList) {
        return Observable.fromCallable(() -> appDatabase.getCorpDao().insertCarpList(corpList));
    }

    @Override
    public Observable<Corp> getCurrentCorp() {
        return Observable.fromCallable(() -> appDatabase.getCorpDao().getCorp());
    }

    @Override
    public Completable wipeCorpData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getCorpDao().wipeCorpTable();
            }
        });
    }

    /**
     *Departament
     */

    @Override
    public Observable<Long> saveDepartament(Departament departament) {
        return Observable.fromCallable((Callable<Long>) () -> appDatabase.getDepartmentDao().insert(departament));
    }

    @Override
    public Observable<Integer> editDepartament(Departament departament) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getDepartmentDao().update(departament));
    }

    @Override
    public Observable<Integer> removeDepartament(Departament departament) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getDepartmentDao().delete(departament));
    }

    @Override
    public Observable<List<Long>> insertDepartamentList(List<Departament> departamentList) {
        return Observable.fromCallable(() -> appDatabase.getDepartmentDao().insertDepartamentList(departamentList));
    }

    @Override
    public Observable<Departament> getCurrentDepartament() {
        return Observable.fromCallable(() -> appDatabase.getDepartmentDao().getDepartament());
    }

    @Override
    public Completable wipeDepartamentData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getDepartmentDao().wipeDepartamentTable();
            }
        });
    }

    /**
     *Job
     */

    @Override
    public Observable<Long> insertJob(Job job) {
        return Observable.fromCallable((Callable<Long>) () -> appDatabase.getJobDao().insert(job));
    }

    @Override
    public Observable<Integer> editJob(Job job) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getJobDao().update(job));
    }

    @Override
    public Observable<Integer> removeJob(Job job) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getJobDao().delete(job));
    }

    @Override
    public Observable<List<Long>> insertJobList(List<Job> jobList) {
        return Observable.fromCallable(() -> appDatabase.getJobDao().insertJobList(jobList));
    }

    @Override
    public Observable<List<Job>> getJobList() {
        return Observable.fromCallable(() -> appDatabase.getJobDao().getJobList());
    }

    @Override
    public Observable<Long> getJobRecordCount() {
        return Observable.fromCallable(() -> appDatabase.getJobDao().getRecordsCount());
    }

    @Override
    public Completable wipeJobData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getJobDao().wipeJobTable();
            }
        });
    }

/*    @Override
    public Observable<List<CategoryWithJob>> getJobWithCategory(Long id) {
        return Observable.fromCallable(() -> appDatabase.getJobDao().getJobWithCategory(id));
    }*/


    /**
     *Project
     */

    @Override
    public long insertProject(Project project) {
        return appDatabase.getProjectDao().insert(project);
    }

    @Override
    public void insertFullProject(Project project, Corp corp, User user, List<Job> joblist) {
        final long corp_id = saveCorp(corp);
        project.setCorp(corp_id);
        final long user_id = saveUser(user);
        project.setUser(user_id);
        final long project_id = insertProject(project);
        if (joblist != null){
            for (Job job : joblist) {
                job.setProjectId(project_id);
                insertJob(job);
            }
        }

    }

    @Override
    public Observable<Integer> editProject(Project project) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getProjectDao().update(project));
    }

    @Override
    public Observable<Integer> removeProject(Project project) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getProjectDao().delete(project));
    }

    @Override
    public Observable<List<Project>> getProjectList() {
        return Observable.fromCallable(() -> appDatabase.getProjectDao().getProjectList());
    }

    @Override
    public Observable<List<Long>> insertProjectList(List<Project> projectList) {
        return Observable.fromCallable(() -> appDatabase.getProjectDao().insertProjectList(projectList));
    }

    @Override
    public Observable<List<ProjectWithJobs>> getProjectWithJobs() {
        return Observable.fromCallable(() -> appDatabase.getProjectDao().getProjectJobs());
    }


    @Override
    public Observable<Long> getProjectRecordCount() {
        return Observable.fromCallable(() -> appDatabase.getProjectDao().getRecordsCount());
    }

    @Override
    public Observable<Project> getCurrentProject(long id) {
        return Observable.fromCallable(() -> appDatabase.getProjectDao().getById(id));
    }

    @Override
    public Completable wipeProjectData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getProjectDao().wipeProjectTable();
            }
        });
    }

    /*
     *User
     */

    @Override
    public long saveUser(User user) {
        return appDatabase.getUserDao().insert(user);
    }

    @Override
    public Observable<Integer> editUser(User user) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getUserDao().update(user));
    }

    @Override
    public Observable<Integer> removeUser(User user) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getUserDao().delete(user));
    }

    @Override
    public Observable<List<Long>> insertUserList(List<User> userList) {
        return Observable.fromCallable(() -> appDatabase.getUserDao().insertUserList(userList));
    }

    @Override
    public Observable<User> getCurrentUser() {
        return Observable.fromCallable(() -> appDatabase.getUserDao().getUser());
    }

    @Override
    public Completable wipeUserData() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                appDatabase.getUserDao().wipeUserTable();
            }
        });
    }

    /**
    *                   Questik
     */
    @Override
    public Observable<Long> saveQuestik(QuestikResponse questikResponse) {
        return Observable.fromCallable((Callable<Long>) () -> appDatabase.getQuestikDao().insert(questikResponse));
    }

    @Override
    public Observable<Integer> editQuestik(QuestikResponse questikResponse) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getQuestikDao().update(questikResponse));
    }

    @Override
    public Observable<Integer> removeQuestik(QuestikResponse questikResponse) {
        return Observable.fromCallable((Callable<Integer>) () -> appDatabase.getQuestikDao().delete(questikResponse));
    }

    @Override
    public Observable<QuestikResponse> getCurrentQuestik() {
        return Observable.fromCallable(() -> appDatabase.getQuestikDao().getQuestik());
    }
}
