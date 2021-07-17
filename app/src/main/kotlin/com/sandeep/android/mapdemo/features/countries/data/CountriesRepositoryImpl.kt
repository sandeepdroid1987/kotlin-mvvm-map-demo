package com.sandeep.android.mapdemo.features.countries.data

import android.content.res.Resources
import android.util.Log
import com.google.gson.Gson
import com.sandeep.android.mapdemo.R
import com.sandeep.android.mapdemo.core.exception.Failure
import com.sandeep.android.mapdemo.core.functional.Either
import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.sandeep.android.mapdemo.features.countries.domain.CountriesRepository
import com.sandeep.android.mapdemo.features.countries.domain.HomeLocation
import java.lang.Exception
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(val resources: Resources, val gson: Gson) : CountriesRepository {

    var homeLocation: HomeLocation? = null

    override fun countries(): Either<Failure, List<Country>> {

        try {
            val text = resources.openRawResource(R.raw.countries)
                    .bufferedReader().use { it.readText() }
            Log.i("CountryList" , "CountriesRepositoryImpl ${text}")

            return Either.Right(gson.fromJson(text, Array<Country>::class.java).toList())
        } catch (e: Exception) {
            Log.i("CountryList" , "CountriesRepositoryImpl ${e}")
            e.printStackTrace()
        }
        return Either.Left(Failure.JsonParsingError)
    }

    override fun saveHome(home: HomeLocation) : Either<Failure, HomeLocation> {
        homeLocation =  home
        return Either.Right(home)
    }

    override fun getHome(): Either<Failure, HomeLocation> {
        homeLocation?.let {
            return Either.Right(it)
        } ?: run {
           return Either.Left(Failure.UnknownValueError)
        }
    }

}