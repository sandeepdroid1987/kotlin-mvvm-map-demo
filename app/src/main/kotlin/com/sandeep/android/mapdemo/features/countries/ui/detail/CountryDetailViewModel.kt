package com.sandeep.android.mapdemo.features.countries.ui.detail


import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.sandeep.android.mapdemo.core.functional.Either
import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.sandeep.android.mapdemo.core.platform.BaseViewModel
import com.sandeep.android.mapdemo.features.countries.data.GetHomeLocation
import com.sandeep.android.mapdemo.features.countries.domain.HomeLocation
import com.sandeep.android.mapdemo.features.countries.ui.model.CountryView
import javax.inject.Inject

class CountryDetailViewModel @Inject constructor(val getHomeLocation: GetHomeLocation) : BaseViewModel() {

    val homeCountry: MutableLiveData<HomeLocationDistance> = MutableLiveData()

    lateinit var currentCountry: CountryView

    fun getHome(current: CountryView)  {
        currentCountry  = current
        getHomeLocation(UseCase.None()){ it.fold(::handleFailure, ::findDistance) }
    }

    fun saveHomeLocation(country: CountryView)  {
        val result = getHomeLocation.save(HomeLocation(country.latitude, country.longitude))
        when(result) {
            is Either.Right -> findDistance(result.b)
        }
    }

    private fun findDistance(homeLocation: HomeLocation?)  {
       homeLocation?.let {
           val startPoint = Location("locationA")
           startPoint.setLatitude(currentCountry.latitude)
           startPoint.setLongitude(currentCountry.longitude)

           val endPoint = Location("locationA")
           endPoint.setLatitude(homeLocation.lat)
           endPoint.setLongitude(homeLocation.lng)

           val distance: Float = startPoint.distanceTo(endPoint)

           homeCountry.postValue(HomeLocationDistance(Math.abs(distance.toLong()/1000)))
       } ?: run {
           homeCountry.postValue(HomeLocationDistance(null))
       }


    }

}