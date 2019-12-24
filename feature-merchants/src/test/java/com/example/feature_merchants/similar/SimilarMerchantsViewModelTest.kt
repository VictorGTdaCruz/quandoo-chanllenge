package com.example.feature_merchants.similar

import com.example.feature_merchants.similar.component.SimilarMerchantsViewModel
import com.example.service_quandoo.merchants.MerchantsUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import com.example.common.component.Resource.Status.LOADING

@RunWith(RobolectricTestRunner::class)
class SimilarMerchantsViewModelTest {

    private lateinit var viewModel: SimilarMerchantsViewModel
    private lateinit var useCase: MerchantsUseCase

    @Before
    fun setup() {
        useCase = mockk(relaxed = true)
        viewModel = SimilarMerchantsViewModel(useCase)
    }

    @Test
    fun `Should call useCase`() {
        viewModel.loadSimilarMerchants(1)

        verify { useCase.getSimilarMerchants("1", any(), any()) }
    }

    @Test
    fun `Should set loading state to live data`() {
        viewModel.loadSimilarMerchants(1)

        assertEquals(viewModel.merchantsList.value?.status, LOADING)
    }
}