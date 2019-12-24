package com.example.feature_merchants.util

import com.example.domain.models.Merchant
import com.example.feature_merchants.dto.ParcelableMerchantDTO

object MerchantMapper {

    fun mapMerchantToParcelableMerchantDTO(merchant: Merchant) =
        merchant.run {
            ParcelableMerchantDTO(
                id = id,
                name = name,
                phoneNumber = phoneNumber,
                street = location?.address?.street,
                number = location?.address?.number,
                zipcode = location?.address?.zipcode,
                city = location?.address?.city,
                country = location?.address?.country,
                reviewScore = reviewScore,
                images = images?.map { it.url }
            )
        }
}