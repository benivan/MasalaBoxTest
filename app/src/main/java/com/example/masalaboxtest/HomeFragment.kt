package com.example.masalaboxtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.work.WorkManager
import com.example.masalaboxtest.adapter.HomeItemAdapter
import com.example.masalaboxtest.databinding.FragmentHomeBinding
import com.example.masalaboxtest.util.Resource
import com.example.masalaboxtest.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeItemAdapter = HomeItemAdapter(emptyList())
        binding.rvHomeItem.adapter = homeItemAdapter

        binding.autoCompleteTv.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Log.d(TAG, "onItemClick: $id")
                homeItemAdapter.filterData(id.toInt())
            }
        viewModel.getNamedData()
        viewModel.namedData.flowWithLifecycle(lifecycle).onEach {
            when (it) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.elFilter.visibility = View.GONE
                }
                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE
                    binding.elFilter.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.elFilter.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    Log.d(TAG, "onViewCreated: ${it.data}")
                    homeItemAdapter.updateData(it.data)
                }
            }
        }.launchIn(lifecycleScope)

        WorkManager.getInstance(requireContext())
            .getWorkInfosByTagLiveData("seed-worker")
            .observe(viewLifecycleOwner) {
                if (it.first().state.isFinished) {
                    viewModel.getNamedData()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        val filter = resources.getStringArray(R.array.filter )
        val arrayAdapter =  ArrayAdapter(requireContext(),R.layout.dropdown_item,filter)
        binding.autoCompleteTv.setAdapter(arrayAdapter)
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}