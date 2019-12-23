package com.example.domain.models

data class SimilarMerchants(
    val merchant: Merchant? = null,
    val recommendedMerchants: MerchantPagedList? = null
)