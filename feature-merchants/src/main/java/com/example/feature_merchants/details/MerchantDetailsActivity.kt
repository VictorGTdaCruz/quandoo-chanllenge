package com.example.feature_merchants.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feature_merchants.R
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import kotlinx.android.synthetic.main.activity_merchant_details.*

class MerchantDetailsActivity : AppCompatActivity() {

    companion object {
        const val MERCHANT_KEY = "merchant_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_details)

        val merchantDetails = intent.extras?.getParcelable<ParcelableMerchantDTO>(MERCHANT_KEY)
        merchantDetailsView.merchant = merchantDetails
    }
}
