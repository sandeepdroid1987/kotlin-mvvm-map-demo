
package com.sandeep.android.mapdemo.core.platform

import androidx.lifecycle.MutableLiveData
import com.sandeep.android.mapdemo.AndroidTest
import com.sandeep.android.mapdemo.core.exception.Failure
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class BaseViewModelTest : AndroidTest() {

    @Test fun `should handle failure by updating live data`() {
        val viewModel = MyViewModel()

        val failure = viewModel.failure

        failure shouldBeInstanceOf MutableLiveData::class.java
    }

    private class MyViewModel : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}