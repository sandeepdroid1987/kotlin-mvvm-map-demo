package com.sandeep.android.mapdemo.features.countries.domain

import com.sandeep.android.mapdemo.core.exception.Failure
import com.sandeep.android.mapdemo.core.functional.Either
import com.sandeep.android.mapdemo.features.countries.data.Country

interface CountriesRepository {
    fun countries(): Either<Failure, List<Country>>
    fun saveHome(homeLocation: HomeLocation): Either<Failure, HomeLocation>
    fun getHome(): Either<Failure, HomeLocation>
}