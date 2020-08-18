package com.example.demogeopagos.presentation.banks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demogeopagos.data.repository.MainRepository
import com.example.demogeopagos.utils.Resource
import kotlinx.coroutines.Dispatchers

class BanksViewModel (private val mainRepository: MainRepository)  : ViewModel() {

    fun getBanks(pms : String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getBanks(pms)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}