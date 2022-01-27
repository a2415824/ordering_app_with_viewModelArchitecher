package com.example.test.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentSummeryBinding
import com.example.test.model.OrderViewModel


class SummeryFragment : Fragment() {

    private var binding:FragmentSummeryBinding?=null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummeryBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summeryFragment = this@SummeryFragment
        }
    }

    fun sendOreder() {
        Toast.makeText(activity,"Send oreder",Toast.LENGTH_LONG).show()
        val numberOfitems = sharedViewModel.quantity.value ?:0
        val orderSummery = getString(
            R.string.order_details,
            sharedViewModel.quantity.value.toString(),
            sharedViewModel.type.value.toString(),
            sharedViewModel.data.value.toString(),
            sharedViewModel.price.value.toString()
        )
        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_SUBJECT,getString(R.string.new_iot_order))
            .putExtra(Intent.EXTRA_TEXT,orderSummery)
        if (activity?.packageManager?.resolveActivity(intent,0)!=null){
            startActivity(intent)
        }
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        Toast.makeText(activity,"Order canceled",Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}