package com.example.quizkotlin.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizkotlin.constants
import com.example.quizkotlin.constants.COLUMN_QUESTION_ID
import com.example.quizkotlin.models.Question
import com.example.quizkotlin.models.Student

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY $COLUMN_QUESTION_ID ASC")
    fun getAllQuestions() : LiveData<List<Question>>

    @Insert
    fun insertQuestion(question: Question)
}