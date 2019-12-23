package com.example.service_quandoo.di

import com.example.domain.services.MerchantsService
import com.example.network.di.networkModule
import com.example.service_quandoo.merchants.MerchantsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val quandooServiceModule = Kodein.Module(name = "quandoo-service") {

    importOnce(networkModule)

    bind<MerchantsService>() with singleton {
        val retrofit: Retrofit = instance()
        retrofit.create(MerchantsService::class.java)
    }

    bind<MerchantsUseCase>() with provider {
        MerchantsUseCase(instance())
    }
}