package ru.rinet.questik.ui.catalog.jobs

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.rinet.questik.repo.QuestikRepository
import ru.rinet.questik.repo.local.room.entity.CategoryEntity
import ru.rinet.questik.repo.local.room.entity.JobsEntity
import ru.rinet.questik.ui.catalog.jobs.epoxy.JobsContainer
import ru.rinet.questik.ui.catalog.jobs.epoxy.JobsPerCategory
import ru.rinet.questik.ui.catalog.jobs.epoxy.OnCategoryExpanded
import ru.rinet.questik.ui.catalog.jobs.epoxy.OnJobsItemClickes
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobsFragmentViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {
    val jobsLiveData: LiveData<JobsContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<JobsContainer>()
    private val onJobClicked: OnJobsItemClickes = {
            Log.i("JobsFragmentViewMOdel", "${it.name} - is clicked")
    }
    private val onCategoryExpanded: OnCategoryExpanded = { category: CategoryEntity ->
        val oldContainer = _liveData.value
        if (oldContainer != null) {
            oldContainer.onCategoryExpanded
            val newCategories = oldContainer.categories.map {
                if (it.category.id == category.id) {
                        it.copy(category = it.category.copy(isExpand = !category.isExpand))

                } else {
                    it
                }
            }
            _liveData.value = oldContainer.copy(categories = newCategories)

        }
    }

    fun search(serched: String){

        /*      val oldContainer = _liveData.value
              if (oldContainer != null) {
                  oldContainer.onCategoryExpanded
                  val newCategories = oldContainer.categories.map {matPerCat->
                      val newMaterials = mutableListOf<MaterialEntity>()
                          initMaterialListByCategory(matPerCat.category.id).apply {
                              this.forEach {
                                  val filteredMat = it.name?.toLowerCase(Locale.getDefault())
                                  if (filteredMat?.contains(serched) == true){
                                      newMaterials.add(it)
                                  }
                              }
                      }
               *//*       if (matPerCat.category.name == category.id) {
                    matPerCat.copy(category = matPerCat.category.copy(isExpand = !category.isExpand))
                } else {
                    matPerCat
                }*//*
                matPerCat.copy(material = newMaterials)
            }
            _liveData.value = oldContainer.copy(categories = newCategories)
        }*/


        /**
         *
         */
        val jobsPerCategory: MutableList<JobsPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            jobsPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var jobList = mutableListOf<JobsEntity>()
                    initJobsListByCategory(it.id).forEach { job->
                        val filteredMat = job.name?.toLowerCase(Locale.getDefault())
                        if (filteredMat?.contains(serched) == true){
                            jobList.add(job)

                        }
                        //println(jobList.size)
                        /*  val newMat = MaterialEntity(mat.id,mat.plu, mat.name, mat.price, mat.metrics_id, mat.category_id)
                          matList.add(newMat)*/
                    }
                    if (jobList.size!=0) {
                        this.add(JobsPerCategory(it, jobList, onJobClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    JobsContainer(jobsPerCategory, onCategoryExpanded)
            }
        }


    }



    fun fetchAllData(){
        val jobsPerCategory: MutableList<JobsPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            jobsPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var jobsList = mutableListOf<JobsEntity>()
                    initJobsListByCategory(it.id).forEach { job->
                        val newjob = JobsEntity(job.id, job.name, job.price, job.metrics_id, job.category_id, job.price_inzh, job.price_nalog_zp, job.price_zp)
                        jobsList.add(newjob)
                    }
                    if (jobsList.size!=0) {
                        this.add(JobsPerCategory(it, jobsList, onJobClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    JobsContainer(jobsPerCategory, onCategoryExpanded)
            }
        }
    }

    init {
        val jobsPerCategory: MutableList<JobsPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            jobsPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var jobsList = mutableListOf<JobsEntity>()
                    initJobsListByCategory(it.id).forEach { job->
                        val newjob = JobsEntity(job.id, job.name, job.price, job.metrics_id, job.category_id, job.price_inzh, job.price_nalog_zp, job.price_zp)
                        jobsList.add(newjob)
                    }
                    if (jobsList.size!=0) {
                        this.add(JobsPerCategory(it, jobsList, onJobClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    JobsContainer(jobsPerCategory, onCategoryExpanded)
            }
        }

    }



    fun initCategoriesList(): List<CategoryEntity> {
        return repository.getCategories().toMutableList()
    }

    fun initJobsListByCategory(categoryId: Int): List<JobsEntity> {
        val catId = categoryId.toString()
        return repository.getJobsByCategoryId(catId).toMutableList()
    }

}