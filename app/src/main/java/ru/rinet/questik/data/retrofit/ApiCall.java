package ru.rinet.questik.data.retrofit;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import ru.rinet.questik.BuildConfig;
import ru.rinet.questik.R;
import ru.rinet.questik.data.retrofit.pojo.LoginRequest;
import ru.rinet.questik.data.retrofit.pojo.MyJobDeserializer;
import ru.rinet.questik.data.retrofit.pojo.QuestikResponse;
import ru.rinet.questik.data.room.models.Corp;
import ru.rinet.questik.data.room.models.Departament;
import ru.rinet.questik.data.room.models.Job;
import ru.rinet.questik.data.room.models.Project;
import ru.rinet.questik.data.room.models.User;
import ru.rinet.questik.utils.Constants;



public interface ApiCall {

    @Headers({
            "Content-Type: application/json",
            "secret-key: $2b$10$c7neKuze3lBQA7bACuFMh.ZHsG3R8qIW9i9q2KwN6v8skLyPk3uni"})
    @GET(ApiEndPoint.ENDPOINT_JOB)
    Observable<QuestikResponse> getQuestik();

    @Headers({
            "Content-Type: application/json",
            "secret-key: $2b$10$c7neKuze3lBQA7bACuFMh.ZHsG3R8qIW9i9q2KwN6v8skLyPk3uni"})
    @POST(ApiEndPoint.ENDPOINT_USER)
    Observable<User> getUserRequest(@Body LoginRequest.ServerLoginRequest request);

    class Factory {
        private static final int NETWORK_CALL_TIMEOUT = 60;

        public static ApiCall create() {


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            /*ApiCall service = retrofit.create(ApiCall.class);
            Call<QuestikResponse> call = service.getJobList();
            call.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(jobList -> {
                        List<Job> jobik = new ArrayList<>();
                        jobik = jobList.getJobs();
                        Log.i("Fragment Presenter", jobList + " JSON");
                        Log.i("Fragment Presenter", jobik + " LIST");
                        if (jobList != null) {

                            Log.i("Fragment Presenter", jobik + " LIST");
                        }
                    });*/


            return retrofit.create(ApiCall.class);


        }

    }
}
