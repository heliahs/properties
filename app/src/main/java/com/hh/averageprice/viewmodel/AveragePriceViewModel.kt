package com.hh.averageprice.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hh.averageprice.network.APIService
import com.hh.averageprice.network.model.Item
import com.hh.averageprice.repository.Repository

class AveragePriceViewModel(private val repository: Repository) : ViewModel() {

    var itemList: LiveData<List<Item>> = MutableLiveData()
    val status = repository.apiStatus

    init {
        fetchDataFromRepository()
    }

    fun fetchDataFromRepository() {
        itemList = repository.fetchDataFromServer()
    }

}