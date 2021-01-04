package com.teambeme.beme.data.local.dao

import androidx.room.*
import com.teambeme.beme.data.local.entity.AnswerData

@Dao
interface AnswerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(answerData: AnswerData)

    @Update
    fun update(answerData: AnswerData)

    @Query("SELECT * FROM answer_record_table WHERE questionId = :key")
    suspend fun get(key: Long): AnswerData?

    @Query("DELETE FROM answer_record_table WHERE questionID = :key")
    fun delete(key: Long)
}