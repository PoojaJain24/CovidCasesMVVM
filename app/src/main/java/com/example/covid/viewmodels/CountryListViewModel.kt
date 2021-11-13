package com.example.covid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.covid.model.MyCountry
import com.example.covid.network.APIClient
import com.example.covid.network.CountryService
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers

class CountryListViewModel() : ViewModel() {
    val countryList = MutableLiveData<List<MyCountry>>()
    val errorMessage = MutableLiveData<String>()

    fun getCountryListObserver() {
        val countryService = APIClient.buildService(CountryService::class.java)
        val responseObservable = countryService.getAffectedCountryList()
        responseObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response->getCountryList(response)},{error->errorMessage.postValue(error.message)})
    }

    private fun getCountryList(response: List<MyCountry>) {
        countryList.postValue(response)
    }
}