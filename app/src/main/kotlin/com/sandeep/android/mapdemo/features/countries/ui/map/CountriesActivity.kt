package com.sandeep.android.mapdemo.features.countries.ui.map

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sandeep.android.mapdemo.R
import com.sandeep.android.mapdemo.MapDemoApp
import com.sandeep.android.mapdemo.core.di.ApplicationComponent
import com.sandeep.android.mapdemo.core.exception.Failure
import com.sandeep.android.mapdemo.core.extension.failure
import com.sandeep.android.mapdemo.core.extension.observe
import com.sandeep.android.mapdemo.core.extension.viewModel
import com.sandeep.android.mapdemo.core.navigation.Navigator
import com.sandeep.android.mapdemo.databinding.ActivityMapsBinding
import com.sandeep.android.mapdemo.features.countries.ui.model.CountryView
import javax.inject.Inject

class CountriesActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    companion object {
        fun callingIntent(context: Context) = Intent(context, CountriesActivity::class.java)
    }

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as MapDemoApp).appComponent
    }

    private lateinit var countriesViewViewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countriesViewViewModel = viewModel(viewModelFactory) {
            observe(countries, ::renderCountriesList)
            failure(failure, ::handleFailure)
        }

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun renderCountriesList(countries: List<CountryView>?) {
        countries?.forEach { country ->
            val latLng = LatLng(country.latitude, country.longitude)
            mMap.addMarker(MarkerOptions().position(latLng).title(country.name).snippet(country.name))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }

        mMap.setOnInfoWindowClickListener { marker ->
            countries?.first { it.name == marker.title }?.let {
                navigator.showDetailActivity(this@CountriesActivity, it)
            }
        }
    }

    private fun handleFailure(failure: Failure?) {
        when (failure) {
            // handle failure
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        countriesViewViewModel.loadCountries()
    }
}
