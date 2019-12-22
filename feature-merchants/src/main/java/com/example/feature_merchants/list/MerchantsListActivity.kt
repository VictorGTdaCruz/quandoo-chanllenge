package com.example.feature_merchants.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature_merchants.R
import com.example.feature_merchants.details.MerchantDetailsActivity
import com.example.feature_merchants.details.MerchantDetailsActivity.Companion.MERCHANT_KEY
import com.example.feature_merchants.di.selfInject
import kotlinx.android.synthetic.main.activity_merchants_list.*
import org.kodein.di.KodeinAware

class MerchantsListActivity : KodeinAware, AppCompatActivity() {

    override val kodein = selfInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchants_list)

        merchantListView.onItemClick = {
            startActivity(Intent(this, MerchantDetailsActivity::class.java)
                .putExtra(MERCHANT_KEY, it))
        }
    }
}
