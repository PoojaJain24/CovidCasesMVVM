package com.example.covid.ui

import android.content.Context
import android.content.Intent
import com.example.covid.model.MyCountry
import java.lang.IllegalArgumentException

object CountryIntent {
    private const val COUNTRY_DETAIL = "country_detail"

    @JvmStatic
    fun createIntent(
        packageContext: Context,
        cls: Class<*>,
        country: MyCountry
    ): Intent {
        return Intent(packageContext, cls).apply {
            putExtra(COUNTRY_DETAIL, country)
        }
    }

    @JvmStatic
    fun getCountry(intent: Intent): MyCountry {
        return intent.getParcelableExtra(COUNTRY_DETAIL)
            ?: throw IllegalArgumentException("Required intent extra $COUNTRY_DETAIL missing")
    }
}