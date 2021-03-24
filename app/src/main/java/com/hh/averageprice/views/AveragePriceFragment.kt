package com.hh.averageprice.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hh.averageprice.R
import com.hh.averageprice.adapter.MyAdapterClickListener
import com.hh.averageprice.adapter.MyListAdapter
import com.hh.averageprice.databinding.FragmentLayoutBinding
import com.hh.averageprice.repository.Repository
import com.hh.averageprice.viewmodel.AveragePriceViewModel
import com.hh.averageprice.viewmodel.AveragePriceViewModelFactory

class AveragePriceFragment : Fragment() {

    lateinit var binding: FragmentLayoutBinding
    lateinit var viewModel: AveragePriceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayoutBinding.inflate(inflater)

val adapter =MyListAdapter(MyAdapterClickListener {
    it.let {
        Toast.makeText(activity,it.address,Toast.LENGTH_SHORT).show()
    }
})

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(activity)


        binding.swipeRefresh.setOnRefreshListener {
        viewModel.fetchDataFromRepository()
            binding.swipeRefresh.isRefreshing = false
        }
        errorCheck()
        viewModel.itemList.observe(viewLifecycleOwner, Observer {
            it?.apply {
                adapter.submitList(it)
            }

        })
        return binding.root
    }

    private fun errorCheck() {
        viewModel.status.observe(viewLifecycleOwner, Observer {
            when(it){
                Repository.ApiStatus.LOADING ->{
                    binding.list.visibility=View.INVISIBLE
                    binding.statusImage.visibility=View.VISIBLE
                    binding.statusImage.setImageResource(R.drawable.loading_animation)
                }
                Repository.ApiStatus.DONE ->{
                    binding.list.visibility=View.VISIBLE
                    binding.statusImage.visibility=View.GONE
                }
                Repository.ApiStatus.ERROR ->{
                    binding.list.visibility=View.INVISIBLE
                     Toast.makeText(activity,resources?.getString(R.string.error), Toast.LENGTH_SHORT).show()
                    binding.statusImage.visibility=View.VISIBLE
                   binding.statusImage.setImageResource(R.drawable.ic_connection_error)
                }
            }

        })
    }

    private fun initViewModel() {
        val viewModelFactory = AveragePriceViewModelFactory()
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AveragePriceViewModel::class.java)
    }



}