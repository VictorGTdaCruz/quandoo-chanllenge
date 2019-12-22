package com.example.domain.models

data class MerchantPagedList(
    val merchants: List<Merchant>? = null,
    val size: Int? = null,
    val offset: Int? = null,
    val limit: Int? = null
)