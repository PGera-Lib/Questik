package ru.rinet.questik.data;

import android.content.Context;


import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;

import io.reactivex.Observable;

import ru.rinet.questik.data.prefs.PreferenceHelper;
import ru.rinet.questik.data.retrofit.ApiHelper;

import ru.rinet.questik.data.retrofit.pojo.LoginRequest;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.DbHelper;
import ru.rinet.questik.data.room.models.Category;
import ru.rinet.questik.data.room.models.CategoryWithJob;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.ProjectWithJobs;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.di.scope.ApplicationContext;


@Singleton
public class AppDataManager implements DataManager {


    private final Context context;
    private final DbHelper dbHelper;
    private final PreferenceHelper preferenceHelper;
    private final ApiHelper apiHelper;

    @Inject
    AppDataManager(@ApplicationContext Context context,
                   DbHelper dbHelper,
                   PreferenceHelper preferenceHelper,
                   ApiHelper apiHelper) {

        this.context = context;
        this.dbHelper = dbHelper;
        this.preferenceHelper = preferenceHelper;
        this.apiHelper = apiHelper;

    }




    //DB
    @Override
    public Observable<Long> saveCategory(Category category) {
        return dbHelper.saveCategory(category);
    }

    @Override
    public Observable<Integer> editCategory(Category category) {
        return dbHelper.editCategory(category);
    }

    @Override
    public Observable<Integer> removeCategory(Category category) {
        return dbHelper.removeCategory(category);
    }

    @Override
    public Observable<Category> getCurrentCategory() {
        return dbHelper.getCurrentCategory();
    }

    @Override
    public Observable<List<Long>> insertCategoryList(List<Category> categoryList) {
        return dbHelper.insertCategoryList(categoryList);
    }

    @Override
    public Observable<List<CategoryWithJob>> getCategoryWithJob() {
        return dbHelper.getCategoryWithJob();
    }

    @Override
    public Completable wipeCategoryData() {
        return dbHelper.wipeCategoryData();
    }

    @Override
    public long saveCorp(Corp corp) {
        return dbHelper.saveCorp(corp);
    }

    @Override
    public Observable<Integer> editCorp(Corp corp) {
        return dbHelper.editCorp(corp);
    }

    @Override
    public Observable<Integer> removeCorp(ru.rinet.questik.data.room.models.Corp corp) {
        return dbHelper.editCorp(corp);
    }

    @Override
    public Observable<List<Long>> insertCorpList(List<Corp> corpList) {
        return dbHelper.insertCorpList(corpList);
    }

    @Override
    public Observable<Corp> getCurrentCorp() {
        return dbHelper.getCurrentCorp();
    }

    @Override
    public Completable wipeCorpData() {
        return dbHelper.wipeCorpData();
    }

    @Override
    public Observable<Long> saveDepartament(Departament departament) {
        return dbHelper.saveDepartament(departament);
    }

    @Override
    public Observable<Integer> editDepartament(Departament departament) {
        return dbHelper.editDepartament(departament);
    }

    @Override
    public Observable<Integer> removeDepartament(Departament departament) {
        return dbHelper.removeDepartament(departament);
    }

    @Override
    public Observable<List<Long>> insertDepartamentList(List<Departament> departamentList) {
        return dbHelper.insertDepartamentList(departamentList);
    }

    @Override
    public Observable<Departament> getCurrentDepartament() {
        return dbHelper.getCurrentDepartament();
    }

    @Override
    public Completable wipeDepartamentData() {
        return dbHelper.wipeDepartamentData();
    }

    @Override
    public Observable<Long> insertJob(Job job) {
        return dbHelper.insertJob(job);
    }

    @Override
    public Observable<Integer> editJob(Job job) {
        return dbHelper.editJob(job);
    }

    @Override
    public Observable<Integer> removeJob(Job job) {
        return dbHelper.removeJob(job);
    }

    @Override
    public Observable<List<Long>> insertJobList(List<Job> jobList) {
        return dbHelper.insertJobList(jobList);
    }

    @Override
    public Observable<List<Job>> getJobList() {
        return dbHelper.getJobList();
    }

    @Override
    public Observable<Long> getJobRecordCount() {
        return dbHelper.getJobRecordCount();
    }

    @Override
    public Completable wipeJobData() {
        return dbHelper.wipeJobData();
    }

/*    @Override
    public Observable<List<CategoryWithJob>> getJobWithCategory(Long id) {
        return dbHelper.getJobWithCategory(id);
    }*/

    @Override
    public long insertProject(Project project) {
        return dbHelper.insertProject(project);
    }

    @Override
    public void insertFullProject(Project project, Corp corp, User user, List<Job> joblist) {
    dbHelper.insertFullProject(project, corp, user, joblist);
    }

    @Override
    public Observable<Integer> editProject(Project project) {
        return dbHelper.editProject(project);
    }

    @Override
    public Observable<Integer> removeProject(Project project) {
        return dbHelper.removeProject(project);
    }

    @Override
    public Observable<List<Project>> getProjectList() {
        return dbHelper.getProjectList();
    }

    @Override
    public Observable<List<Long>> insertProjectList(List<Project> projectList) {
        return dbHelper.insertProjectList(projectList);
    }

    @Override
    public Observable<List<ProjectWithJobs>> getProjectWithJobs() {
        return dbHelper.getProjectWithJobs();
    }


    @Override
    public Observable<Long> getProjectRecordCount() {
        return dbHelper.getProjectRecordCount();
    }

    @Override
    public Observable<Project> getCurrentProject(long id) {
        return dbHelper.getCurrentProject(id);
    }

    @Override
    public Completable wipeProjectData() {
        return dbHelper.wipeProjectData();
    }



    @Override
    public long saveUser(User user) {
        return dbHelper.saveUser(user);
    }

    @Override
    public Observable<Integer> editUser(User user) {
        return dbHelper.editUser(user);
    }

    @Override
    public Observable<Integer> removeUser(User user) {
        return dbHelper.removeUser(user);
    }

    @Override
    public Observable<List<Long>> insertUserList(List<User> userList) {
        return dbHelper.insertUserList(userList);
    }

    @Override
    public Observable<User> getCurrentUser() {
        return dbHelper.getCurrentUser();
    }

    @Override
    public Completable wipeUserData() {
        return dbHelper.wipeUserData();
    }




    @Override
    public Observable<Long> saveQuestik(QuestikResponse questikResponse) {
        return dbHelper.saveQuestik(questikResponse);
    }

    @Override
    public Observable<Integer> editQuestik(QuestikResponse questikResponse) {
        return dbHelper.editQuestik(questikResponse);
    }

    @Override
    public Observable<Integer> removeQuestik(QuestikResponse questikResponse) {
        return dbHelper.removeQuestik(questikResponse);
    }

    @Override
    public Observable<QuestikResponse> getCurrentQuestik() {
        return dbHelper.getCurrentQuestik();
    }
    @Override
    public Observable<QuestikResponse> getQuestik() {
        return apiHelper.getQuestik();
    }

    @Override
    public Observable<User> getUserRequest(LoginRequest.ServerLoginRequest request) {
        return apiHelper.getUserRequest(request);
    }


    @Override
    public void setCurrentUserId(Long id) {
        preferenceHelper.setCurrentUserId(id);
    }

    @Override
    public Long getCurrentUserId() {
        return preferenceHelper.getCurrentUserId();
    }

    @Override
    public void setCurrentUserName(String userName) {
        preferenceHelper.setCurrentUserName(userName);
    }
    @Override
    public String getCurrentUserName() {
        return preferenceHelper.getCurrentUserName();
    }

    @Override
    public void setCurrentUserEmail(String email) {
        preferenceHelper.setCurrentUserEmail(email);
    }

    @Override
    public String getCurrentUserEmail() {
        return preferenceHelper.getCurrentUserEmail();
    }

    @Override
    public void updateUserInfoInPrefs(Long userId, String userName, String userEmail, LoggedInMode mode) {
        preferenceHelper.updateUserInfoInPrefs(userId, userName, userEmail, mode);
    }
    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        preferenceHelper.setCurrentUserLoggedInMode(mode);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return preferenceHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedOut() {
        preferenceHelper.setCurrentUserLoggedOut();
    }


    /**
     *     Firebase
     *
     * @param name
     * @param mail
     * @param password
     * @return
     */
/*
    @Override
    public void registerUserFireBase(String name, String mail, String password) {
         fireBaseHelper.registerUserFireBase(name, mail, password);
    }

    @Override
    public void loginUserFireBase(String mail, String password) {
         fireBaseHelper.loginUserFireBase(mail, password);
    }
*/

}
