package com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.findNavController
import com.example.test.R

import com.example.test.databinding.FragmentStartBinding
import com.example.test.model.OrderViewModel


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val sharedViewModel:OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this

    }

    fun orderIot() {

        findNavController().navigate(R.id.action_startFragment_to_TypeFragment2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}