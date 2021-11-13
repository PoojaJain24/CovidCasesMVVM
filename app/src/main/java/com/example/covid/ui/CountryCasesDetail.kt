package com.example.covid.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.covid.model.MyCountry
import com.example.covidcasesmvvm.databinding.CountryDetailBinding
import com.squareup.picasso.Picasso

class CountryCasesDetail : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun newIntent(
            context: Context,
            country: MyCountry
        ) = CountryIntent.createIntent(
            context,
            CountryCasesDetail::class.java,
            country
        )
    }

    private lateinit var binding: CountryDetailBinding
    private val country by lazy { CountryIntent.getCountry(intent) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
    }

    private fun setupViews() {
        Picasso.with(this).load(country.countryInfo.flag)
            .into(binding.ivFlag)
        binding.tvCountryName.text = country.country
        binding.tvCasesValue.text = country.cases.toString()
        binding.tvDeathValue.text = country.deaths.toString()
        binding.tvTestValue.text = country.tests.toString()
        binding.tvPopulationValue.text = country.population.toString()
    }
}