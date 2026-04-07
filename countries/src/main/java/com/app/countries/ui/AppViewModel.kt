package com.app.countries.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.base.data.network.BaseResource
import com.app.countries.domain.useCases.GetAllCountriesUseCases
import com.app.countries.domain.useCases.GetDetailCountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    val getAllCountriesUseCases: GetAllCountriesUseCases,
    val getDetailCountryUseCases: GetDetailCountryUseCases
)  : ViewModel(){


    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getAllCountriesUseCases.invoke()
            when (response) {
                is BaseResource.Success -> {
                    val countries = response.data
                    println("deyber : success : data : $countries")
                }

                is BaseResource.Loading -> {
                    println("deyber : loading")
                }

                is BaseResource.Error -> {
                    val error = response.error
                    println("deyber : error : data : $error")
                }
            }
        }
    }

    fun getCountryDetail(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = getDetailCountryUseCases.invoke("Mexico")
            when(response){
                is BaseResource.Success -> {
                    println("deyber : success : data : ${response.data}")
                }
                is BaseResource.Loading -> {
                    println("deyber : loading")
                }
                is BaseResource.Error -> {
                    println("deyber : error : data : ${response.error}")
                }
            }
        }
    }
}