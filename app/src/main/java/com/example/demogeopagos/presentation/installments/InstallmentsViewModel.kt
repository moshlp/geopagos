package com.example.demogeopagos.presentation.installments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demogeopagos.data.repository.MainRepository
import com.example.demogeopagos.utils.Resource
import kotlinx.coroutines.Dispatchers

class InstallmentsViewModel (private val mainRepository: MainRepository)  : ViewModel() {

    fun getInstallments(pms : String, amount : String, issuer_id : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getInstallments(pms , amount, issuer_id)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}