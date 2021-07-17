
package com.sandeep.android.mapdemo.features.countries

import com.sandeep.android.mapdemo.AndroidTest
import com.sandeep.android.mapdemo.core.functional.Either.Right
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.given
import com.sandeep.android.mapdemo.features.countries.data.Country
import com.sandeep.android.mapdemo.features.countries.data.GetCountries
import com.sandeep.android.mapdemo.features.countries.ui.map.CountriesViewModel
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldEqualTo
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class CountiresViewModelTest : AndroidTest() {

    private lateinit var countriesViewModel: CountriesViewModel

    @Mock private lateinit var getCountries: GetCountries

    @Before
    fun setUp() {
        countriesViewModel = CountriesViewModel(getCountries)
    }

    @Test fun `loading countries should update live data`() {
        val countriesList = listOf(Country(listOf(),listOf(),"UK", "" , ""))
        given { runBlocking { getCountries.run(eq(any())) } }.willReturn(Right(countriesList))

        countriesViewModel.countries.observeForever {
            it!!.size shouldEqualTo 1
            it[0].name shouldEqualTo "UK"
        }

        runBlocking { countriesViewModel.loadCountries() }
    }
}