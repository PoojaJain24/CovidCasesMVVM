package com.example.covid.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.model.MyCountry
import com.example.covidcasesmvvm.databinding.CountryRowItemBinding
import com.squareup.picasso.Picasso

class CountriesAdapter(private val clickListener: (MyCountry) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    var countryList = ArrayList<MyCountry>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CountryRowItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding) {
            clickListener(countryList[it])
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(countryList[position])
    }
    class ViewHolder(binding: CountryRowItemBinding, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        private var imageViewCountryFlag: ImageView = binding.ivCountryFlag
        var textViewCountryName: TextView = binding.tvCountryName

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }

        fun bind(country: MyCountry) {
            textViewCountryName.text = country.country
            Picasso.with(itemView.context).load(country.countryInfo.flag)
                .into(imageViewCountryFlag)
        }
    }
}