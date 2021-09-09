package xyz.gmfatoom.questik.repo.remote.retrofit

import io.reactivex.rxjava3.core.Observable
import xyz.gmfatoom.questik.repo.remote.models.*
import xyz.gmfatoom.questik.repo.remote.retrofit.response.QuestikResponse

interface IApiHelper {
    fun getQuestik(apiKey:String): Observable<QuestikResponse>
    fun getCategories():Observable<List<CategoryModel>>
    fun getJobs():Observable<List<JobsModel>>
    fun getMaterials():Observable<List<MaterialModel>>
    fun getMetrics(): Observable<List<MetricsModel>>
    fun getUsers():Observable<List<UserModel>>
}