package com.example.domain.services

import com.example.domain.models.MerchantPagedList
import com.example.domain.models.SimilarMerchants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MerchantsService {

    @GET("v1/merchants")
    fun getList(@QueryMap parameters: Map<String, String>): Call<MerchantPagedList>

    @GET("v1/merchants/{merchantId}/similar")
    fun getSimilar(@Path("merchantId") merchantId: String): Call<SimilarMerchants>
}