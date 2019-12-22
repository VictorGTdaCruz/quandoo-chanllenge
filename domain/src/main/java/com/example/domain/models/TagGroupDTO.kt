package com.example.domain.models

data class TagGroupDTO(
    val type: String? = null,
    val tags: List<TranslatedTagDTO>? = null
)
