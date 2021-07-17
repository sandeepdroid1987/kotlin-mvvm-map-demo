package com.sandeep.android.mapdemo.features.countries.ui.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.sandeep.android.mapdemo.core.platform.BaseViewModel
import com.sandeep.android.mapdemo.features.countries.data.Country
import com.sandeep.android.mapdemo.features.countries.data.GetCountries
import com.sandeep.android.mapdemo.features.countries.ui.model.CountryView
import javax.inject.Inject

class CountriesViewModel @Inject constructor(val getCountries: GetCountries) : BaseViewModel() {

    var countries: MutableLiveData<List<CountryView>> = MutableLiveData()

    fun loadCountries() = getCountries(UseCase.None()) { it.fold(::handleFailure, ::handleCountriesList) }

    private fun handleCountriesList(characters: List<Country>) {
        Log.i("CountryList" , "handleCountriesList ${characters?.size}")

        this.countries.value = characters.map { CountryView(name = it.name, latitude = it.latlng[0], longitude = it.latlng[1], capital = it.capital) }
    }

}