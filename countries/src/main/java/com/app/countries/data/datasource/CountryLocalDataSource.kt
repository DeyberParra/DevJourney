package com.app.countries.data.datasource

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class CountryLocalDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getCountriesInputStream(): InputStream {
        return context.assets.open("countries_data.xml")
    }
}
