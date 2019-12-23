package com.example.feature_merchants.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.common.selfInject
import com.example.feature_merchants.R
import com.example.feature_merchants.details.MerchantDetailsActivity
import com.example.feature_merchants.details.MerchantDetailsActivity.Companion.MERCHANT_KEY
import kotlinx.android.synthetic.main.activity_merchants_list.*
import org.kodein.di.KodeinAware

class MerchantsListActivity : KodeinAware, AppCompatActivity() {

    override val kodein = selfInject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchants_list)

        title = getString(R.string.merchants_screen_title)

        merchantListView.onItemClick = {
            startActivity(Intent(this, MerchantDetailsActivity::class.java)
                .putExtra(MERCHANT_KEY, it))
        }
    }
}
