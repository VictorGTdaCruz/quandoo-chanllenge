package com.example.network.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val networkModule = Kodein.Module(name = "network") {

    bind<Retrofit>() with singleton {
        Retrofit.Builder()
            .baseUrl("api.quandoo.com")
            .client(instance())
            .build()
    }

    bind<OkHttpClient>() with singleton {
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()
    }
}