package com.example.demogeopagos.presentation.payment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demogeopagos.data.repository.MainRepository
import com.example.demogeopagos.utils.Resource
import kotlinx.coroutines.Dispatchers

class PaymentMethodsViewModel(private val mainRepository: MainRepository)  : ViewModel() {

    fun getPaymentMethods() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getPaymentMethods()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }




}