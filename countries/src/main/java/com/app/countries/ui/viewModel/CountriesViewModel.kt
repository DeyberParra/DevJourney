package com.app.countries.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.app.base.data.network.BaseResource
import com.app.base.ui.BaseViewModel
import com.app.countries.data.model.response.CountryDetailModel
import com.app.countries.domain.model.CountryModel
import com.app.countries.domain.useCases.GetAllCountriesUseCases
import com.app.countries.domain.useCases.GetDetailCountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    val getAllCountriesUseCases: GetAllCountriesUseCases,
    val getDetailCountryUseCases: GetDetailCountryUseCases
)  : BaseViewModel(){

    init {
        getCountries()
    }
    private val _countriesState = MutableStateFlow<List<CountryModel>>(emptyList())


    private val _detailState = MutableStateFlow<CountryDetailModel?>(null)
    val detailState = _detailState.asStateFlow()

    //Status search
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    val filteredCountries: StateFlow<List<CountryModel>> = combine(
        _countriesState,
        _searchQuery
    ) { list, query ->
        if (query.isBlank()) {
            list
        } else {

            list.filter { it.name?.contains(query, ignoreCase = true) == true }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(5000), // Mantiene el estado vivo 5s tras salir de la pantalla
        initialValue = emptyList()
    )

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }


    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()
            val response = getAllCountriesUseCases.invoke()
            when (response) {
                is BaseResource.Success -> {
                    _countriesState.value = response.data
                    hideLoading()
                }

                is BaseResource.Error -> {
                    val error = response.error
                    showError(error.message)
                    hideLoading()
                }
            }
        }
    }

    fun getCountryDetail(countryName : String){
        viewModelScope.launch(Dispatchers.IO) {
            showLoading()
            val response = getDetailCountryUseCases.invoke(countryName)
            when(response){
                is BaseResource.Success -> {
                    val country = response.data
                    if(country.isNotEmpty()){
                        _detailState.value = response.data.first()
                    }

                    hideLoading()
                }
                is BaseResource.Error -> {
                    showError(response.error.message)
                    hideLoading()
                }
            }
        }
    }
}