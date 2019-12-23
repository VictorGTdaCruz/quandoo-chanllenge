package com.example.service_quandoo.merchants

import com.example.domain.models.MerchantPagedList
import com.example.domain.models.SimilarMerchants
import com.example.domain.services.MerchantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MerchantsUseCase(private val merchantsService: MerchantsService) {

    fun getMerchantList(parameters: Map<String, String>,
                        onSuccess: (MerchantPagedList?) -> Unit,
                        onError: (Throwable) -> Unit) {
        merchantsService.getList(parameters).enqueue(object : Callback<MerchantPagedList> {
            override fun onResponse(call: Call<MerchantPagedList>, response: Response<MerchantPagedList>) {
                onSuccess(response.body())
            }
            override fun onFailure(call: Call<MerchantPagedList>, throwable: Throwable) {
                onError(throwable)
            }
        })
    }

    fun getSimilarMerchants(merchantId: String,
                        onSuccess: (SimilarMerchants?) -> Unit,
                        onError: (Throwable) -> Unit) {
        merchantsService.getSimilar(merchantId).enqueue(object : Callback<SimilarMerchants> {
            override fun onResponse(call: Call<SimilarMerchants>, response: Response<SimilarMerchants>) {
                onSuccess(response.body())
            }
            override fun onFailure(call: Call<SimilarMerchants>, throwable: Throwable) {
                onError(throwable)
            }
        })
    }
}