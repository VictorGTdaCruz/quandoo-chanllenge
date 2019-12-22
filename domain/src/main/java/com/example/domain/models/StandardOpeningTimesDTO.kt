package com.example.domain.models

data class StandardOpeningTimesDTO(
    val monday: List<OpeningTime>? = null,
    val tuesday: List<OpeningTime>? = null,
    val wednesday: List<OpeningTime>? = null,
    val thursday: List<OpeningTime>? = null,
    val friday: List<OpeningTime>? = null,
    val saturday: List<OpeningTime>? = null,
    val sunday: List<OpeningTime>? = null
)
