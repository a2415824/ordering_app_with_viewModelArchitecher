package com.example.test.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentPickUpBinding
import com.example.test.model.OrderViewModel


class PickUpFragment : Fragment() {


   private var binding:FragmentPickUpBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPickUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner =viewLifecycleOwner
            viewModel=sharedViewModel

            pickFragment = this@PickUpFragment
        }
    }

    fun goToNextScreen() {
        Toast.makeText(activity,"Next",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        Toast.makeText(activity,"Order canceled",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}