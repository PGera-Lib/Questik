package ru.rinet.questik.ui.catalog.material

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
import ru.rinet.questik.repo.local.room.entity.MaterialEntity
import ru.rinet.questik.ui.catalog.material.epoxy.MaterialContainer
import ru.rinet.questik.ui.catalog.material.epoxy.MaterialPerCategory
import ru.rinet.questik.ui.catalog.material.epoxy.OnCategoryExpanded
import ru.rinet.questik.ui.catalog.material.epoxy.OnMaterialItemClickes
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MaterialFragmentViewModel @Inject constructor(
    private val repository: QuestikRepository
) : ViewModel() {



    val materialLiveData: LiveData<MaterialContainer>
        get() = _liveData
    private val _liveData = MutableLiveData<MaterialContainer>()
    private val onMaterialClicked: OnMaterialItemClickes = {
            Log.i("MaterialFragmentViewMOdel", "${it.name} - is clicked")
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
        val materialPerCategory: MutableList<MaterialPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            materialPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var matList = mutableListOf<MaterialEntity>()
                    initMaterialListByCategory(it.id).forEach { mat->
                        val filteredMat = mat.name?.toLowerCase(Locale.getDefault())
                        if (filteredMat?.contains(serched) == true){
                            matList.add(mat)

                        }
                       // println(matList.size)
                      /*  val newMat = MaterialEntity(mat.id,mat.plu, mat.name, mat.price, mat.metrics_id, mat.category_id)
                        matList.add(newMat)*/
                    }
                    if (matList.size!=0) {
                        this.add(MaterialPerCategory(it, matList, onMaterialClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    MaterialContainer(materialPerCategory, onCategoryExpanded)
            }
        }


    }
    fun fetchAllData(){
        val materialPerCategory: MutableList<MaterialPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            materialPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var matList = mutableListOf<MaterialEntity>()
                    initMaterialListByCategory(it.id).forEach { mat->
                        val newMat = MaterialEntity(mat.id,mat.plu, mat.name, mat.price, mat.metrics_id, mat.category_id)
                        matList.add(newMat)
                    }
                    if (matList.size!=0) {
                        this.add(MaterialPerCategory(it, matList, onMaterialClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    MaterialContainer(materialPerCategory, onCategoryExpanded)
            }
        }
    }
    init {
        val materialPerCategory: MutableList<MaterialPerCategory> = mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            materialPerCategory.apply {
                val categories = initCategoriesList()
                categories.forEach {
                    var matList = mutableListOf<MaterialEntity>()
                    initMaterialListByCategory(it.id).forEach { mat->
                        val newMat = MaterialEntity(mat.id,mat.plu, mat.name, mat.price, mat.metrics_id, mat.category_id)
                        matList.add(newMat)
                    }
                    if (matList.size!=0) {
                        this.add(MaterialPerCategory(it, matList, onMaterialClicked))
                    }
                }
            }
            CoroutineScope(Dispatchers.Main).launch {
                _liveData.value =
                    MaterialContainer(materialPerCategory, onCategoryExpanded)
            }
        }

    }



    fun initCategoriesList(): List<CategoryEntity> {
        return repository.getCategories().toMutableList()
    }

    fun initMaterialListByCategory(categoryId: Int): List<MaterialEntity> {
        val catId = categoryId.toString()
        return repository.getMaterialByCategoryId(catId).toMutableList()
    }

}