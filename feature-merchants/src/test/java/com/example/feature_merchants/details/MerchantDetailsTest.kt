package com.example.feature_merchants.details

import androidx.fragment.app.FragmentActivity
import com.example.feature_merchants.details.component.MerchantDetailsView
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import kotlinx.android.synthetic.main.widget_merchant_details.view.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MerchantDetailsTest {

    private lateinit var component: MerchantDetailsView
    private lateinit var activity: FragmentActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(FragmentActivity::class.java).create().get()
        component = MerchantDetailsView(activity)
    }

    @Test
    fun `Should bind merchant information`() {
        val merchant = ParcelableMerchantDTO(
            name = "rest",
            street = "street",
            city = "rio",
            country = "Br",
            zipcode = "123",
            reviewScore = "2"
        )

        component.run {
            this.merchant = merchant

            assertEquals(merchantDetailsName.text, "rest")
            assertEquals(merchantDetailsStreet.text, "street")
            assertEquals(merchantDetailsCity.text, "rio, Br - 123")
            assertEquals(merchantDetailsRating.text, "2 out of 6")
        }
    }

}