package com.example.covid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid.model.MyCountry
import com.example.covid.viewmodels.CountryListViewModel
import com.example.covidcasesmvvm.databinding.ActivityMainBinding

class CountryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var countryAdapter: CountriesAdapter
    private lateinit var countryListViewModel: CountryListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countryListViewModel = ViewModelProvider(this).get(CountryListViewModel::class.java)
        setupViews()
    }

    private fun setupViews() {
        binding.countryRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@CountryListActivity)
            countryAdapter = CountriesAdapter() {
                onCountryItemClicked(it)
            }
            adapter = countryAdapter
        }
        binding.fetchCountryButton.setOnClickListener {
            showProgress()
            showCountryList()
        }
    }

    private fun showCountryList() {
        binding.fetchCountryButton.isVisible = false
        countryListViewModel.countryList.observe(this, Observer {
            hideProgress()
            countryAdapter.countryList = it as ArrayList<MyCountry>
            binding.countryRecycler.isVisible = true
            countryAdapter.notifyDataSetChanged()
        })
        countryListViewModel.errorMessage.observe(this, Observer {

        })
        countryListViewModel.getCountryListObserver()
    }

    private fun onCountryItemClicked(country: MyCountry) {
        val intent: Intent = CountryCasesDetail.newIntent(this, country)
        this.startActivity(intent)
    }

    private fun showProgress() {
        binding.progressBar.isVisible = true
    }

    private fun hideProgress() {
        binding.progressBar.isVisible = false
    }
}