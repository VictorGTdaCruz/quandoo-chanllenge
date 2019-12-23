package com.example.feature_merchants.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.common.selfInject
import com.example.feature_merchants.R
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import com.example.feature_merchants.util.MerchantMapper
import kotlinx.android.synthetic.main.activity_merchant_details.*
import org.kodein.di.KodeinAware

class MerchantDetailsActivity : KodeinAware, AppCompatActivity() {

    override val kodein = selfInject()

    companion object {
        const val MERCHANT_KEY = "merchant_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_details)

        title = getString(R.string.merchant_details_screen_title)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val merchantDetails = intent.extras?.getParcelable<ParcelableMerchantDTO>(MERCHANT_KEY)

        merchantDetailsView.merchant = merchantDetails
        similarMerchantsView.run {
            merchantId = merchantDetails?.id
            onItemClick = {
                startActivity(Intent(context, this@MerchantDetailsActivity::class.java)
                    .putExtra(MERCHANT_KEY, it))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
