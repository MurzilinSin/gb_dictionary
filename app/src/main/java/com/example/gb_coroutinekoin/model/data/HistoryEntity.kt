package com.example.gb_coroutinekoin.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey
    val word: String,
    val description: String?
)