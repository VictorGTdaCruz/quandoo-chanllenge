package com.example.feature_merchants.list.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.component.Resource
import com.example.common.component.Resource.Status.*
import com.example.domain.models.Merchant
import com.example.domain.models.MerchantPagedList
import com.example.service_quandoo.merchants.MerchantsUseCase

class MerchantListViewModel(
    private val merchantsUseCase: MerchantsUseCase
): ViewModel() {

    companion object {
        private const val LIMIT = 30
    }

    val merchantsList: MutableLiveData<Resource<MerchantPagedList>> = MutableLiveData()

    fun loadPage(pageToLoad: Int) {
        if (pageToLoad == 0) merchantsList.postValue(Resource(
            status = LOADING,
            data = merchantsList.value?.data ?: MerchantPagedList()
        ))

        merchantsUseCase.getMerchantList(
            parameters = mapOf(
                "limit" to LIMIT.toString(),
                "offset" to pageToLoad.toString()
            ),
            onSuccess = {
                it?.let {
                    val oldItems = merchantsList.value?.data?.merchants ?: mutableListOf()
                    val totalItems = oldItems.apply { addAll(it.merchants) }
                    it.merchants = totalItems
                    merchantsList.postValue(Resource(SUCCESS, data = it))
                }
            },
            onError =  {
                merchantsList.postValue(Resource(
                    status = ERROR,
                    data = merchantsList.value?.data ?: MerchantPagedList(),
                    error = it))
            }
        )
    }

    fun isLastPage() =
        merchantsList.value?.data?.run {
            ((offset ?: 0) + 1) * (limit ?: 0) >= size ?: 0
        } ?: false

    fun shouldMakeInitialRequests() = merchantsList.value == null
}