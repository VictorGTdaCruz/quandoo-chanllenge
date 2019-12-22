package com.example.domain.models

data class LocationDTO(
    val coordinates: CoordinatesDTO? = null,
    val address: MerchantAddressDTO? = null
)