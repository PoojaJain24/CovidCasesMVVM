package com.example.covid.network

import com.example.covid.model.MyCountry
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {
    @GET("countries")
    suspend fun getAffectedCountryList () : Response<List<MyCountry>>
}