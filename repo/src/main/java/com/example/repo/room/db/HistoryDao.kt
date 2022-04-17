package com.example.repo.room.db

import androidx.room.*

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history")
    fun getAll(): List<HistoryEntity>

    @Query("SELECT * FROM history WHERE word =:word")
    fun getByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: HistoryEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryEntity>)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}