package com.example.gb_coroutinekoin.model.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.data.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
abstract class DataBase: RoomDatabase() {
    abstract val historyDao: HistoryDao
}