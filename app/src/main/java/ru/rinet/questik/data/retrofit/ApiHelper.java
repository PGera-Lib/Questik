package ru.rinet.questik.data.retrofit;


import java.io.File;
import java.util.List;

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


public interface ApiHelper {

   /* Observable<User> doServerLoginApiCall(LoginRequest.ServerLoginRequest request);

    Observable<List<Corp>> doCorpListApiCall();

    Observable<List<Departament>> doDepartmentListApiCall();
*/
   Observable<QuestikResponse> getQuestik();

   Observable<User> getUserRequest(LoginRequest.ServerLoginRequest request);

   /* Observable<List<Project>> doProjectListApiCall();

    Observable<List<User>> doUserListApiCall();

    Observable<QuestikResponse> doQuestikApiCall();*/


}
