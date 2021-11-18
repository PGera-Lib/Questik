package ru.rinet.questik.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.rinet.questik.MainActivity

lateinit var APP_ACTIVITY: MainActivity
const val NAME_KEY_API_QUESTIK = "$2b$10\$c7neKuze3lBQA7bACuFMh.ZHsG3R8qIW9i9q2KwN6v8skLyPk3uni"
const val BASE_URL = "https://api.jsonbin.io/b/"

object ConstantData {
    const val ENDPOINT_JOB: String = BASE_URL +
            "5eac9c5e4c87c3359a65173d/7"
    const val ENDPOINT_USER: String = BASE_URL +
            "5ec7f5e1e91d1e45d10f500b/1"
}

object Util{
    fun provideRetrofit(gson: Gson = GsonBuilder().create()) : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}
object NetworkUtils {
    fun isNetworkConnected(context: Context): Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val networkCapabilities = connectivityManager.activeNetwork?:return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)?:return false
            result = when{
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else{
            @Suppress("DEPRECATION")
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when(type){
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }
}