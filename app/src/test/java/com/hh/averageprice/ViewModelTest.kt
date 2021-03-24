package com.hh.averageprice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.hh.averageprice.network.model.Item
import com.hh.averageprice.repository.Repository
import com.hh.averageprice.viewmodel.AveragePriceViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {
    lateinit var viewModel: AveragePriceViewModel

    @Mock
    lateinit var repository: Repository
    lateinit var itemsList : List<Item>
    private val mockList: MutableList<Item> = mutableListOf()
   private val mockLiveData : MutableLiveData<List<Item>> = MutableLiveData<List<Item>>()


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        this.viewModel= AveragePriceViewModel(repository)
        mockData()
    }

    @Test
    fun fetchDataFromRepository(){
        Mockito.lenient().`when`(repository.fetchDataFromServer()).thenReturn(mockLiveData)
        viewModel.fetchDataFromRepository()
        Assert.assertNotNull(viewModel.itemList.value)
        Assert.assertTrue(viewModel.itemList.value?.size == 3)
        Assert.assertEquals(viewModel.itemList, mockLiveData)
    }

    private fun mockData(){
        itemsList= emptyList()

        mockList.add(Item(1000,"test1"))
        mockList.add(Item(2000,"test2"))
        mockList.add(Item(3000,"test3"))

        itemsList= mockList.toList()
       mockLiveData.postValue(itemsList)


    }
}