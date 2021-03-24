package com.hh.averageprice

import android.app.Application
import android.content.Context
import com.hh.averageprice.network.APIUrl
import com.hh.averageprice.network.di.APIComponent
import com.hh.averageprice.network.di.APIModule
import com.hh.averageprice.network.di.DaggerAPIComponent

class MyApplication: Application() {


    companion object {
        var ctx: Context? = null
        lateinit var APIComponent: APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        APIComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return APIComponent
    }

    fun initDaggerComponent():APIComponent{
        APIComponent =   DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIUrl.BASE_URL))
            .build()
        return  APIComponent

    }
}