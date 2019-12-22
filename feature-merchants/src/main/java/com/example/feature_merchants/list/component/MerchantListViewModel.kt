package com.example.feature_merchants.list.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.MerchantPagedList
import com.example.domain.services.MerchantsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MerchantListViewModel(
    private val merchantsService: MerchantsService
): ViewModel() {

    val merchantsList: MutableLiveData<MerchantPagedList> = MutableLiveData()

    fun loadNextPage() {
        merchantsService.getList().enqueue(object : Callback<MerchantPagedList> {
            override fun onFailure(call: Call<MerchantPagedList>, t: Throwable) {
                merchantsList.postValue(MerchantPagedList())
            }

            override fun onResponse(call: Call<MerchantPagedList>, response: Response<MerchantPagedList>) {
                merchantsList.postValue(response.body())
            }
        })
    }

}