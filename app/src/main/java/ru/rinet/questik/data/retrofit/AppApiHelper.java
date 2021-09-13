package ru.rinet.questik.data.retrofit;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import ru.rinet.questik.data.retrofit.pojo.LoginRequest;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;


@Singleton
public class AppApiHelper implements ApiHelper{

    private ApiCall apiCall;

    @Inject
    AppApiHelper(ApiCall apiCall) {
        this.apiCall = apiCall;
    }


/*    @Override
    public Observable<User> doServerLoginApiCall(LoginRequest.ServerLoginRequest request) {
        return apiCall.doServerLogin(request);
    }

    @Override
    public Observable<List<Corp>> doCorpListApiCall() {
        return apiCall.getCorpList();
    }

    @Override
    public Observable<List<Departament>> doDepartmentListApiCall() {
        return apiCall.getDepartmentList();
    }*/

    @Override
    public Observable<QuestikResponse> getQuestik() {
        return apiCall.getQuestik();
    }

    @Override
    public Observable<User> getUserRequest(LoginRequest.ServerLoginRequest request) {
        return apiCall.getUserRequest(request);
    }

/*
    @Override
    public Observable<List<Project>> doProjectListApiCall() {
        return apiCall.getProjectList();
    }

    @Override
    public Observable<List<User>> doUserListApiCall() {
        return apiCall.getUserList();
    }
*/

/*    @Override
    public Observable<QuestikResponse<List<Job>>> doQuestikListJobApiCall() {
        return apiCall.getJob();
    }*/


}
