
package com.sandeep.android.mapdemo.core.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sandeep.android.mapdemo.features.countries.ui.detail.CountryDetailViewModel
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(countriesViewViewModel: CountriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailViewModel::class)
    abstract fun bindCountryDetailViewModel(countryDetailViewModel: CountryDetailViewModel): ViewModel
}