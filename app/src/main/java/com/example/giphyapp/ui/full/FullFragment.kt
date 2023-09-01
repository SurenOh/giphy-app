package com.example.giphyapp.ui.full

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.giphyapp.databinding.FragmentFullBinding
import com.example.giphyapp.ui.BaseFragment

class FullFragment : BaseFragment<FragmentFullBinding>() {
    private val args: FullFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener { popBackStack() }
        Glide.with(requireContext())
            .load(args.link)
            .into(binding.gif)
    }

    override fun inflateBinding() = FragmentFullBinding.inflate(layoutInflater)
}