package com.example.covid.network

import com.example.covid.model.MyCountry
import io.reactivex.Observable
import retrofit2.http.GET

interface CountryService {
    @GET("countries")
    fun getAffectedCountryList () : Observable<List<MyCountry>>
}