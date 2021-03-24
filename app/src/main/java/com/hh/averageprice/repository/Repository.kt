package com.hh.averageprice.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hh.averageprice.MyApplication
import com.hh.averageprice.R
import com.hh.averageprice.network.APIService
import com.hh.averageprice.network.di.APIComponent
import com.hh.averageprice.network.model.Item
import com.hh.averageprice.network.model.Properties
import com.hh.averageprice.viewmodel.AveragePriceViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class Repository {
    enum class ApiStatus { LOADING, ERROR, DONE }

    var itemList:  MutableLiveData<List<Item>> = MutableLiveData()
    var apiStatus : MutableLiveData<ApiStatus> = MutableLiveData()


    @Inject
    lateinit var retrofit: Retrofit

    init {
        val apiComponent: APIComponent = MyApplication.APIComponent
        apiComponent.inject(this)
    }


    fun fetchDataFromServer(): LiveData<List<Item>>{
        apiStatus.value =ApiStatus.LOADING
        val apiService: APIService = retrofit.create(APIService::class.java)
        val serverListData: Call<Properties> = apiService.getData()
        serverListData.enqueue(object : Callback<Properties> {
            override fun onResponse(call: Call<Properties>, response: Response<Properties>) {
                val serverResponse = response.body()
                itemList.value = serverResponse!!.properties
                apiStatus.value =ApiStatus.DONE
            }

            override fun onFailure(call: Call<Properties>, t: Throwable) {
                apiStatus.value =ApiStatus.ERROR
                }

        })
        return itemList
    }

}