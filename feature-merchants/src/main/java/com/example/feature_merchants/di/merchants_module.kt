package com.example.feature_merchants.di

import com.example.feature_merchants.list.component.MerchantListViewModel
import com.example.feature_merchants.similar.component.SimilarMerchantsViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val merchantsModule = Kodein.Module(name = "merchants") {

    bind<MerchantListViewModel>() with provider {
        MerchantListViewModel(instance())
    }

    bind<SimilarMerchantsViewModel>() with provider {
        SimilarMerchantsViewModel(instance())
    }
}