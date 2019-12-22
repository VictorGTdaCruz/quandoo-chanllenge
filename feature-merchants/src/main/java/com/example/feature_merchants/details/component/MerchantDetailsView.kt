package com.example.feature_merchants.details.component

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide
import com.example.common.component.LifecycleOwnerConstraintLayout
import com.example.feature_merchants.R
import com.example.feature_merchants.dto.ParcelableMerchantDTO
import com.example.feature_merchants.list.component.GenericAdapter
import kotlinx.android.synthetic.main.item_merchant_details_image.view.*
import kotlinx.android.synthetic.main.widget_merchant_details.view.*

class MerchantDetailsView: LifecycleOwnerConstraintLayout {

    var merchant: ParcelableMerchantDTO? = null
        set(value) {
            field = value
            setupLayout(value)
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        inflate(context, R.layout.widget_merchant_details, this)
    }

    private fun setupLayout(merchant: ParcelableMerchantDTO?) {
        merchantDetailsName.text = merchant?.name ?: ""
        merchantDetailsStreet.text = merchant?.street ?: ""
        merchantDetailsCity.text =
            String.format(resources.getString(R.string.merchant_details_address),
                merchant?.city ?: "",
                merchant?.country ?: "",
                merchant?.zipcode ?: "")
        merchantDetailsRating.text =
            String.format(resources.getString(R.string.merchant_details_rating),
                merchant?.reviewScore ?: "")

        merchantDetailsImages.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        merchantDetailsImages.adapter = GenericAdapter(
            items = merchant?.images ?: emptyList(),
            viewType = { R.layout.item_merchant_details_image },
            bindHolder = {
                Glide.with(context).load(it)
                    .error(R.drawable.ic_restaurant_black_24dp)
                    .into(itemMerchantDetailsImage)
            }
        )
    }
}
