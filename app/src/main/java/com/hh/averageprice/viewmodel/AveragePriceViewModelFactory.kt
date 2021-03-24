package com.hh.averageprice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hh.averageprice.MyApplication
import com.hh.averageprice.network.di.APIComponent
import com.hh.averageprice.repository.Repository
import javax.inject.Inject

class AveragePriceViewModelFactory :ViewModelProvider.Factory{

    @Inject
    lateinit var repository: Repository

   override fun <T : ViewModel> create(modelClass: Class<T>): T {

       val apiComponent :APIComponent =  MyApplication.APIComponent
       apiComponent.inject(this)
       if (modelClass.isAssignableFrom(AveragePriceViewModel::class.java)) {
           return AveragePriceViewModel(repository) as T
       }
       throw IllegalArgumentException("Unknown ViewModel class")
   }
}