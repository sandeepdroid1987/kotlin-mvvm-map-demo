package com.sandeep.android.mapdemo.features.countries.data

import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.sandeep.android.mapdemo.features.countries.domain.CountriesRepository
import javax.inject.Inject

class GetCountries  @Inject constructor(private val countriesRespository: CountriesRepository) : UseCase<List<Country>, UseCase.None>() {

    override suspend fun run(params: None) = countriesRespository.countries()
}