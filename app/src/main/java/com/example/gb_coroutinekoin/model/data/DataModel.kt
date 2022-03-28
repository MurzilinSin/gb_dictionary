package com.example.gb_coroutinekoin.model.data

import kotlinx.serialization.Serializable

@Serializable
data class DataModel(
    val id: String?,
    val text: String?,
    val meanings: List<Meanings>?

)

@Serializable
class Meanings(
    val translation: Translation?,
    val note: String?,
    val previewImageUrl: String?,
    val imageUrl: String?,
    val transcription: String?,
    val soundUrl: String?
)

@Serializable
class Translation(
    val text: String?,
    val note: String?)
