package com.example.gb_coroutinekoin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class DataModel(
    val id: String?,
    val text: String? = null,
    val meanings: List<Meanings>? = null
)

@Serializable
class Meanings(
    val translation: Translation?,
    val note: String? = null,
    val previewImageUrl: String? = null,
    val imageUrl: String? = null,
    val transcription: String? = null,
    val soundUrl: String? = null
)

@Serializable
class Translation(
    val text: String?,
    val note: String? = null
)