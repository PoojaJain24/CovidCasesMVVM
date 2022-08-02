package com.example.covid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid.model.MyCountry
import com.example.covid.network.APIClient
import com.example.covid.network.CountryService
import com.example.covid.network.Repository
import kotlinx.coroutines.*

class CountryListViewModel : ViewModel() {
    val countryList = MutableLiveData<List<MyCountry>>()
    val errorMessage = MutableLiveData<String>()
    var handleJob: Job? = null

    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            onError("Exception : ${throwable.localizedMessage}")
        }

    fun getCountryList() {
        val countryService = APIClient.buildService(CountryService::class.java)
        val repository = Repository(countryService)
        handleJob = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getAffectedCountryList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countryList.postValue(response.body())
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(msg: String) {
        errorMessage.postValue(msg)
    }

    override fun onCleared() {
        super.onCleared()
        handleJob?.cancel()
    }
}