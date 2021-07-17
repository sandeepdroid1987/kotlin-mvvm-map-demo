
package com.sandeep.android.mapdemo.countries

import com.sandeep.android.mapdemo.UnitTest
import com.sandeep.android.mapdemo.core.functional.Either.Right
import com.sandeep.android.mapdemo.core.interactor.UseCase
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.sandeep.android.mapdemo.features.countries.data.Country
import com.sandeep.android.mapdemo.features.countries.data.GetCountries
import com.sandeep.android.mapdemo.features.countries.domain.CountriesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class GetCountriesTest : UnitTest() {

    private lateinit var getCountries: GetCountries

    @Mock private lateinit var countriesRepository: CountriesRepository

    @Before fun setUp() {
        getCountries = GetCountries(countriesRepository)
        given { countriesRepository.countries() }.willReturn(Right(listOf(Country(listOf(), listOf(), "", "", "" ))))
    }

    @Test fun `should get data from repository`() {
        runBlocking { getCountries.run(UseCase.None()) }

        verify(countriesRepository).countries()
        verifyNoMoreInteractions(countriesRepository)
    }
}
