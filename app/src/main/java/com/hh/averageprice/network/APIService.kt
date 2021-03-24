package com.hh.averageprice.network

import com.hh.averageprice.network.model.Properties
import retrofit2.Call
import retrofit2.http.GET

interface APIService  {

    @GET("rightmove/Code-Challenge-Android/master/properties.json")
    fun getData() : Call<Properties>

}