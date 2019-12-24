package com.example.feature_merchants.list

import com.example.common.component.Resource
import com.example.domain.models.MerchantPagedList
import com.example.feature_merchants.list.component.MerchantListViewModel
import com.example.service_quandoo.merchants.MerchantsUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MerchantListViewModelTest {

    private lateinit var viewModel: MerchantListViewModel
    private lateinit var useCase: MerchantsUseCase

    @Before
    fun setup() {
        useCase = mockk(relaxed = true)
        viewModel = MerchantListViewModel(useCase)
    }


    @Test
    fun `Should call useCase`() {
        viewModel.loadPage(0)

        verify { useCase.getMerchantList(any(), any(), any()) }
    }

    @Test
    fun `Should set loading state to live data`() {
        viewModel.loadPage(0)

        assertEquals(viewModel.merchantsList.value?.status, Resource.Status.LOADING)
    }

    @Test
    fun `Should not set loading state to live data`() {
        viewModel.loadPage(1)

        assertNull(viewModel.merchantsList.value?.status)
    }

    @Test
    fun `Should be last page`() {
        viewModel.merchantsList.postValue(
            Resource(
                status = Resource.Status.SUCCESS,
                data = MerchantPagedList(
                    size = 100,
                    offset = 10,
                    limit = 10
                )
            ))
        assertTrue(viewModel.isLastPage())
    }

    @Test
    fun `Should not be last page`() {
        viewModel.merchantsList.postValue(
            Resource(
                status = Resource.Status.SUCCESS,
                data = MerchantPagedList(
                    size = 100,
                    offset = 9,
                    limit = 10
                )
            ))
        assertTrue(viewModel.isLastPage())
    }

    @Test
    fun `Should make initial request`() {
        assertTrue(viewModel.shouldMakeInitialRequests())
    }

    @Test
    fun `Should not make initial request`() {
        viewModel.merchantsList.postValue(
            Resource(
                status = Resource.Status.SUCCESS,
                data = MerchantPagedList()
            ))
        assertFalse(viewModel.shouldMakeInitialRequests())
    }
}