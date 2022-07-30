package xyz.gmfatoom.questik.repo.remote.retrofit

import io.reactivex.rxjava3.core.Observable
import xyz.gmfatoom.questik.repo.remote.models.*
import xyz.gmfatoom.questik.repo.remote.retrofit.response.QuestikResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiHelper @Inject constructor() :IApiHelper {
    override fun getQuestik(apiKey: String): Observable<QuestikResponse> {
        TODO("Not yet implemented")
    }

    override fun getCategories(): Observable<List<CategoryModel>> {
        TODO("Not yet implemented")
    }

    override fun getJobs(): Observable<List<JobsModel>> {
        TODO("Not yet implemented")
    }

    override fun getMaterials(): Observable<List<MaterialModel>> {
        TODO("Not yet implemented")
    }

    override fun getMetrics(): Observable<List<MetricsModel>> {
        TODO("Not yet implemented")
    }

    override fun getUsers(): Observable<List<UserModel>> {
        TODO("Not yet implemented")
    }

}