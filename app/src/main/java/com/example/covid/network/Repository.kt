package com.example.covid.network

class Repository constructor(private val countryService: CountryService){
    suspend fun getAffectedCountryList () = countryService.getAffectedCountryList()
}