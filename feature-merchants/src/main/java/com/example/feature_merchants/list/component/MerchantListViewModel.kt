package com.example.feature_merchants.list.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.component.Resource
import com.example.domain.models.MerchantPagedList
import com.example.common.component.Resource.Status.LOADING
import com.example.common.component.Resource.Status.SUCCESS
import com.example.common.component.Resource.Status.ERROR
import com.example.service_quandoo.merchants.MerchantsUseCase

class MerchantListViewModel(
    private val merchantsUseCase: MerchantsUseCase
): ViewModel() {

    val merchantsList: MutableLiveData<Resource<MerchantPagedList>> = MutableLiveData()

    fun loadNextPage() {
        merchantsList.postValue(Resource(LOADING))
        merchantsUseCase.getMerchantList(
            onSuccess = { merchantsList.postValue(Resource(SUCCESS, data = it)) },
            onError =  { merchantsList.postValue(Resource(ERROR, error = it)) }
        )
    }
}