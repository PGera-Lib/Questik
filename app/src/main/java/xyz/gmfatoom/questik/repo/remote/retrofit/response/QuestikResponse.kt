package xyz.gmfatoom.questik.repo.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import xyz.gmfatoom.questik.repo.local.room.entity.*

data class QuestikResponse (
    @SerializedName("info") var info : Info,
    @SerializedName("category") var category : List<CategoryEntity>,
    @SerializedName("material") var material : List<MaterialEntity>,
    @SerializedName("jobs") var jobs : List<JobsEntity>,
    @SerializedName("metrics") var metrics : List<MetricsEntity>,
    @SerializedName("users") var users : UserEntity
    )