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
import com.example.test.databinding.FragmentTypesBinding
import com.example.test.model.OrderViewModel

class TypesFragment : Fragment() {

    private var binding:FragmentTypesBinding?=null
    private val sharedViewModel: OrderViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentTypesBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            typeFragment = this@TypesFragment
        }
    }

    fun goToNextScreen() {
        Toast.makeText(activity, "Next", Toast.LENGTH_SHORT).show()
        val quli = binding?.quantity?.text.toString()
        sharedViewModel.setQuantity(quli)
        findNavController().navigate(R.id.action_TypeFragment_to_pickupFragment)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        Toast.makeText(activity,"Order canceled",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_TypeFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}