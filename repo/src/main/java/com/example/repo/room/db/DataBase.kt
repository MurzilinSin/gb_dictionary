package com.example.repo.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
abstract class DataBase: RoomDatabase() {
    abstract val historyDao: HistoryDao
}