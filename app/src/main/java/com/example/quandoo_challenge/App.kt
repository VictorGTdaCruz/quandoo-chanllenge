package com.example.quandoo_challenge

import android.app.Application
import com.example.feature_merchants.di.merchantsServiceModule
import com.example.service_quandoo.di.quandooServiceModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class App : Application(), KodeinAware {

    private val appModule = Kodein.Module(name = "application") {
        bind() from provider { this }
    }

    override val kodein = Kodein {
        import(appModule)
        import(merchantsServiceModule)
        import(quandooServiceModule)
    }
}