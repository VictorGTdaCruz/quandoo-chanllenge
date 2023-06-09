package com.example.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.instanceOrNull
import org.kodein.di.generic.provider

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> KodeinAware.viewModel() = lazy {

    val factory = object : ViewModelProvider.Factory {
        override fun <Model : ViewModel> create(klass: Class<Model>) =
            direct.instance<VM>() as Model
    }

    val host = direct.instanceOrNull<FragmentActivity>() ?: throw IllegalStateException()

    ViewModelProviders.of(host, factory).get(VM::class.java)
}

fun AppCompatActivity.selfInject() = Kodein.lazy {

    val parentKodein = (applicationContext as KodeinAware).kodein

    extend(parentKodein)

    bind<AppCompatActivity>() with provider {
        this@selfInject
    }
}

fun View.selfInject() = Kodein.lazy {

    val parentKodein = (context as KodeinAware).kodein

    extend(parentKodein)

    bind<View>() with provider {
        this@selfInject
    }
}