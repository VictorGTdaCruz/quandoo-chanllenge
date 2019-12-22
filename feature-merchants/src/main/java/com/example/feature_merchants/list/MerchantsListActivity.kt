package com.example.feature_merchants.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature_merchants.R
import com.example.feature_merchants.di.selfInject
import org.kodein.di.KodeinAware

class MerchantsListActivity : KodeinAware, AppCompatActivity() {

    override val kodein = selfInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchants_list)
    }
}
