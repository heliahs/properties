package com.hh.averageprice.network.di

import com.hh.averageprice.AppModule
import com.hh.averageprice.repository.Repository
import com.hh.averageprice.viewmodel.AveragePriceViewModel
import com.hh.averageprice.viewmodel.AveragePriceViewModelFactory
import com.hh.averageprice.views.AveragePriceFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,APIModule::class])
interface APIComponent {
    fun inject(repository: Repository)
    fun inject(viewModelFactory: AveragePriceViewModelFactory)
    fun inject (averagePriceViewModel : AveragePriceViewModel)
    fun inject (fragment: AveragePriceFragment)
}


