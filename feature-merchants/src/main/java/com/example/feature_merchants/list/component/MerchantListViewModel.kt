package com.example.feature_merchants.list.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.common.component.Resource
import com.example.domain.models.MerchantPagedList
import com.example.domain.services.MerchantsService
import com.example.common.component.Resource.Status.LOADING
import com.example.common.component.Resource.Status.SUCCESS
import com.example.common.component.Resource.Status.ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MerchantListViewModel(
    private val merchantsService: MerchantsService
): ViewModel() {

    val merchantsList: MutableLiveData<Resource<MerchantPagedList>> = MutableLiveData()

    fun loadNextPage() {
        merchantsList.postValue(Resource(LOADING))
        merchantsService.getList().enqueue(object : Callback<MerchantPagedList> {
            override fun onResponse(call: Call<MerchantPagedList>, response: Response<MerchantPagedList>) {
                merchantsList.postValue(Resource(SUCCESS, data = response.body()))
            }

            override fun onFailure(call: Call<MerchantPagedList>, throwable: Throwable) {
                merchantsList.postValue(Resource(ERROR, error = throwable))
            }
        })
    }
}