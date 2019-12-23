package com.example.feature_merchants.similar.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.component.Resource
import com.example.domain.models.Merchant
import com.example.service_quandoo.merchants.MerchantsUseCase

class SimilarMerchantsViewModel(
    private val merchantsUseCase: MerchantsUseCase
): ViewModel() {

    val merchantsList: MutableLiveData<Resource<List<Merchant>>> = MutableLiveData()

    fun loadSimilarMerchants(merchantId: Int) {
        merchantsList.postValue(
            Resource(
                status = Resource.Status.LOADING,
                data = merchantsList.value?.data ?: mutableListOf()
            )
        )

        merchantsUseCase.getSimilarMerchants(
            merchantId = merchantId.toString(),
            onSuccess = {similar ->
                similar?.recommendedMerchants?.let {
                    merchantsList.postValue(Resource(Resource.Status.SUCCESS, data = it.merchants))
                }
            },
            onError =  {
                merchantsList.postValue(
                    Resource(status = Resource.Status.ERROR,
                        data = merchantsList.value?.data ?: mutableListOf(),
                        error = it)
                )
            }
        )
    }

}