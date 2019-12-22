package com.example.feature_merchants.dto

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelableMerchantDTO(
    val id: Int? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    val street: String? = null,
    val number: String? = null,
    val zipcode: String? = null,
    val city: String? = null,
    val country: String? = null,
    val reviewScore: String? = null,
    val images: List<String?>? = null,
//    val openingTimes: OpeningTimesDTO? = null,
    val shortDescription: String? = null
): Parcelable