package com.example.masalaboxtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.masalaboxtest.adapter.DiscoverSecondItemAdapter
import com.example.masalaboxtest.adapter.DiscoverThirdItemAdapter
import com.example.masalaboxtest.adapter.DiscoverTopItemAdapter
import com.example.masalaboxtest.databinding.FragmentDiscoverBinding
import com.example.masalaboxtest.util.Resource
import com.example.masalaboxtest.viewmodels.DiscoverVIewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DiscoverFragment : Fragment() {

    private var _binding: FragmentDiscoverBinding? = null
    private val binding: FragmentDiscoverBinding get() = _binding!!

    private val viewMode: DiscoverVIewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentDiscoverBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewMode.getTopData()
        viewMode.getMiddleData()
        viewMode.getItemDetail()

        val discoverThirdItemAdapter = DiscoverThirdItemAdapter(emptyList())
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.thirdRecyclerView.layoutManager = layoutManager
        binding.thirdRecyclerView.adapter = discoverThirdItemAdapter

        val discoverTopAdapter = DiscoverTopItemAdapter(emptyList())
        binding.topPictureRecyclerView.adapter = discoverTopAdapter

        viewMode.topData.flowWithLifecycle(lifecycle).onEach {
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                   discoverTopAdapter.submitList(it.data)
                }
                is Resource.Failure -> {

                }
            }
        }.launchIn(lifecycleScope)

        viewMode.middleData.flowWithLifecycle(lifecycle).onEach {
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    val discoverSecondAdapter = DiscoverSecondItemAdapter(it.data)
                    binding.secondPictureRecyclerView.adapter = discoverSecondAdapter
                }
                is Resource.Failure -> {

                }
            }
        }.launchIn(lifecycleScope)

        viewMode.itemDetailData.flowWithLifecycle(lifecycle).onEach {
            when(it){
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    discoverThirdItemAdapter.updateList(it.data)
                }
                is Resource.Failure -> {

                }
            }
        }.launchIn(lifecycleScope)
    }
}