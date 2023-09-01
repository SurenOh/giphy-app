package com.example.giphyapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphyapp.databinding.FragmentHomeBinding
import com.example.giphyapp.ui.BaseFragment
import com.example.giphyapp.util.isInternetAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModel()
    private val adapter = HomeAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupRecycler()
        setupAdapter()
        getGifs()
    }

    private fun setupRecycler() {
        binding.recycler.apply {
            adapter = this@HomeFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getGifs() {
        if (requireContext().isInternetAvailable()) viewModel.getGifs() else showErrorMessage { getGifs() }
    }

    private fun nextPage(offset: Int) {
        if (requireContext().isInternetAvailable()) viewModel.nextPage(offset) else showErrorMessage { nextPage(offset) }
    }

    private fun setupAdapter() {
        adapter.onClickImage = { imageUrl ->
            navigate(HomeFragmentDirections.toFull(imageUrl))
        }
        adapter.loadMore = { offset ->
            nextPage(offset)
        }
    }

    private fun setupObservers() {
        viewModel.gifs.observe(viewLifecycleOwner) { gifts ->
            if (gifts.isNotEmpty()) adapter.addItems(gifts) else showErrorMessage { nextPage(adapter.itemCount) }
        }

        viewModel.firstGifts.observe(viewLifecycleOwner) { gifts ->
            if (gifts.isNotEmpty()) adapter.setItems(gifts) else showErrorMessage { getGifs() }
        }
    }

    override fun inflateBinding() = FragmentHomeBinding.inflate(layoutInflater)
}