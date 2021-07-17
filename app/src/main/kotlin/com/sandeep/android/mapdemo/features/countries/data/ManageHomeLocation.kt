package com.sandeep.android.mapdemo.features.countries.data

import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.sandeep.android.mapdemo.features.countries.domain.CountriesRepository
import com.sandeep.android.mapdemo.features.countries.domain.HomeLocation
import javax.inject.Inject

class ManageHomeLocation  @Inject constructor(private val countriesRespository: CountriesRepository) : UseCase<HomeLocation, UseCase.None>() {

    override suspend fun run(params: None) = countriesRespository.getHome()
}