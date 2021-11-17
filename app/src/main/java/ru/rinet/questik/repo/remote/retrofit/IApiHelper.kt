package ru.rinet.questik.repo.remote.retrofit

import io.reactivex.rxjava3.core.Observable
import ru.rinet.questik.models.*
import ru.rinet.questik.repo.remote.retrofit.response.QuestikResponse

interface IApiHelper {
    fun getQuestik(apiKey:String): Observable<QuestikResponse>
    fun getCategories():Observable<List<CategoryModel>>
    fun getJobs():Observable<List<JobsModel>>
    fun getMaterials():Observable<List<MaterialModel>>
    fun getMetrics(): Observable<List<MetricsModel>>
    fun getUsers():Observable<List<UserModel>>
}