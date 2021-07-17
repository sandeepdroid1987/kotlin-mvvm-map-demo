package com.sandeep.android.mapdemo.features.countries.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.sandeep.android.mapdemo.MapDemoApp
import com.sandeep.android.mapdemo.R
import com.sandeep.android.mapdemo.core.di.ApplicationComponent
import com.sandeep.android.mapdemo.core.exception.Failure
import com.sandeep.android.mapdemo.core.extension.failure
import com.sandeep.android.mapdemo.core.extension.observe
import com.sandeep.android.mapdemo.core.extension.viewModel
import com.sandeep.android.mapdemo.databinding.ActivityCountryDetailBinding
import com.sandeep.android.mapdemo.features.countries.domain.HomeLocation
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesViewModel
import com.sandeep.android.mapdemo.features.countries.ui.model.CountryView
import javax.inject.Inject


class CountriesDetailActivity: AppCompatActivity(), OnMapReadyCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityCountryDetailBinding

    companion object {
        private const val INTENT_EXTRA_PARAM_COUNTRY = "country"

        fun callingIntent(context: Context, countryView: CountryView): Intent {
            val intent = Intent(context, CountriesDetailActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_COUNTRY, countryView)
            return intent
        }
    }

    private val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as MapDemoApp).appComponent
    }

    private lateinit var countryDetailViewModel: CountryDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        binding = ActivityCountryDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        countryDetailViewModel = viewModel(viewModelFactory) {
            observe(homeCountry, ::updateHomeDistance)
            failure(failure, ::handleFailure)
        }
        intent.extras?.getParcelable<CountryView>(INTENT_EXTRA_PARAM_COUNTRY)?.run {
            countryDetailViewModel.getHome(this)
            binding.countryCapital.text  = this.capital
            binding.countryTitle.text  = this.name
            binding.countryLocation.text = "${this.latitude},${this.longitude}"

            binding.markHome.setOnClickListener {
                Snackbar.make(it, "Marking this as home", Snackbar.LENGTH_LONG).show()
                countryDetailViewModel.saveHomeLocation(this)
            }
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.detail_map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    private fun updateHomeDistance(homeCountryDistance: HomeLocationDistance?) {
        homeCountryDistance?.takeIf { it.distance!=null }?.let {

            binding.homeDistance.text =  when (it.distance!! == 0.toLong()) {
                 true -> "This is your home country now!"
                 false -> "${it.distance} kms from Home"
            }
        } ?: run {
            binding.homeDistance.text = "Home Unknown"
        }
    }


    private fun handleFailure(failure: Failure?) {
        binding.homeDistance.text = "Home Unknown"
    }
    override fun onMapReady(googleMap: GoogleMap) {
        intent.extras?.getParcelable<CountryView>(INTENT_EXTRA_PARAM_COUNTRY)?.let { country ->
            val latLng = LatLng(country.latitude, country.longitude)
            googleMap.addMarker(MarkerOptions().position(latLng).title(country.name).snippet(country.name))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        }

    }
}