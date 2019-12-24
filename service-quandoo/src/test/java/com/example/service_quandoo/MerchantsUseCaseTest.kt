package com.example.service_quandoo

import com.example.domain.models.MerchantPagedList
import com.example.domain.models.SimilarMerchants
import com.example.domain.services.MerchantsService
import com.example.service_quandoo.merchants.MerchantsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class MerchantsUseCaseTest {

    private lateinit var service: MerchantsService
    private lateinit var useCase: MerchantsUseCase

    private val emptyMap = emptyMap<String, String>()
    private val emptyString = ""

    @Before
    fun setup() {
        service = mockk(relaxed = true)
        useCase = MerchantsUseCase(service)
    }

    @Test
    fun `Should useCase call merchant list service`() {
        val call: Call<MerchantPagedList> = mockk()

        every { service.getList(emptyMap) } returns call
        useCase.getMerchantList(emptyMap, {}, {})
        verify { service.getList(emptyMap).enqueue(any()) }
    }

    @Test
    fun `Should useCase call similar merchant service`() {
        val call: Call<SimilarMerchants> = mockk()

        every { service.getSimilar(emptyString) } returns call
        useCase.getSimilarMerchants(emptyString, {}, {})
        verify { service.getSimilar(emptyString).enqueue(any()) }
    }
}