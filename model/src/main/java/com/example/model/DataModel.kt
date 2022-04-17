package com.example.model

import com.google.gson.annotations.SerializedName

class DataModel(
    @field:SerializedName("id") val id: String?,
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meanings>?
)


class Meanings(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("note") val note: String? = null,
    @field:SerializedName("previewImageUrl") val previewImageUrl: String? = null,
    @field:SerializedName("imageUrl") val imageUrl: String? = null,
    @field:SerializedName("transcription") val transcription: String? = null,
    @field:SerializedName("soundUrl") val soundUrl: String? = null
)

class Translation(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("note") val note: String? = null
)