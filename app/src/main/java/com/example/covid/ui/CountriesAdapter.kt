package com.example.covid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid.model.MyCountry
import com.example.covidcasesmvvm.R
import com.squareup.picasso.Picasso

class CountriesAdapter(private val clickListener: (MyCountry) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {
    var countryList = ArrayList<MyCountry>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.country_row_item, parent, false)
        return ViewHolder(view) {
            clickListener(countryList[it])
        }
    }

    override fun getItemCount(): Int {
        return countryList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(countryList[position])
    }

    class ViewHolder(itemView: View, clickAtPosition: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        var imageViewCountryFlag: ImageView = itemView.findViewById(R.id.ivCountryFlag)
        var textViewCountryName: TextView = itemView.findViewById(R.id.tvCountryName)

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