package com.example.domain.services

import com.example.domain.models.MerchantPagedList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MerchantsService {

    @GET("v1/merchants")
    fun getList(@QueryMap parameters: Map<String, String>): Call<MerchantPagedList>
}