package com.example.domain.models

data class MerchantPagedList(
    var merchants: MutableList<Merchant> = mutableListOf(),
    val size: Int? = null,
    val offset: Int? = null,
    val limit: Int? = null
)