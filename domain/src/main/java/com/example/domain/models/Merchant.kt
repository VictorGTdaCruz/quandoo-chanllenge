package com.example.domain.models

data class Merchant(
    val id: Int? = null,
    val name: String? = null,
    val phoneNumber: String? = null,
    val currency: String? = null,
    val locale: String? = null,
    val timezone: String? = null,
    val location: LocationDTO? = null,
    val reviewScore: String? = null,
    val tagGroups: List<TagGroupDTO>? = null,
    val images: List<ImageDTO>? = null,
    val documents: List<DocumentDTO>? = null,
    val links: List<LinkRelationDTO>? = null,
    val bookable: Boolean? = null,
    val openingTimes: OpeningTimesDTO? = null,
    val ccvEnabled: Boolean? = null,
    val chain: ChainDTO? = null,
    val ivrRedirectNumber: String? = null,
    val shortDescription: String? = null,
    val longDescription: String? = null
)