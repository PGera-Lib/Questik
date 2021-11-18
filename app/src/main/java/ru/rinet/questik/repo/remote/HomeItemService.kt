package ru.rinet.questik.repo.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.rinet.questik.repo.remote.retrofit.response.QuestikResponse

interface HomeItemService {

    @GET("/")
    suspend fun getAllItems(@Query("page")page:Int): Response<QuestikResponse>

/*    suspend fun getFireBaseJobs(page: Int): List<JobsEntity>{
        val list = mutableListOf<JobsEntity>()
        initListJob(page){
           for (j in CATALOG_LIST_JOB) {
               val job : JobsEntity = JobsEntity()
               job.id = j.id?.toInt()!!
               job.name = j.name
               job.price = j.price
               job.metrics_id = j.metrics_id
               job.category_id = j.category_id
               job.price_inzh = j.price_inzh
               job.price_nalog_zp = j.price_nalog_zp
               job.price_zp = j.price_zp
               list.add(job)
           }
        }

        return list
    }*/


}