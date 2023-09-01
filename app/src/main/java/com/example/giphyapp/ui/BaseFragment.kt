package com.example.giphyapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<VB: ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun inflateBinding() : VB

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (_binding == null) _binding = inflateBinding()
        return binding.root
    }

    fun navigate(dir: NavDirections) {
        findNavController().navigate(dir)
    }

    fun popBackStack() {
        findNavController().popBackStack()
    }

    fun showErrorMessage(action: () -> Unit) {
        val message = "Something went wrong, please try later!"
        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Refresh") { action() }
        snackBar.show()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}