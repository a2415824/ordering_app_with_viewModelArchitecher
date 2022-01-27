package com.example.test.model

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


private const val PRICE_FOR_SAMEDAY = 10.00


class OrderViewModel:ViewModel() {

    val dateOptions = getPickUpOptions()

    private val _quantity = MutableLiveData<Int>()
    val quantity:LiveData<Int> = _quantity

    private val _type = MutableLiveData<String>()
    val type:LiveData<String> = _type

    private val _date = MutableLiveData<String>()
    val data: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price :LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }
    private val _priceperItem = MutableLiveData<Double>()
    val priceperItem :LiveData<String> = Transformations.map(_price){
        NumberFormat.getCurrencyInstance().format(it)
    }

    init {
        resetOrder()
    }

    fun setQuantity(numberofitem: String){
        _quantity.value = numberofitem.toInt()
        updatePrice()
    }
    fun setType(desireditem:String,priceInrupee:Double){
        _type.value = desireditem
        _priceperItem.value = priceInrupee
    }
    fun setDate(pickupDate:String){
        _date.value = pickupDate
        updatePrice()
    }
    fun hasTypeSet():Boolean{
        return _type.value.isNullOrEmpty()
    }
    private fun getPickUpOptions():List<String>{
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4){
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE,1)
        }
        return options
    }
    fun resetOrder(){
        _quantity.value = 0
        _type.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    private fun updatePrice(){
        var calculatedPrice = (quantity.value?:0)* _priceperItem.value!!
        if(dateOptions[0] == _date.value){
            calculatedPrice += PRICE_FOR_SAMEDAY
        }
        _price.value = calculatedPrice
    }


}