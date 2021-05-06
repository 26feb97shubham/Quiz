package com.example.quizkotlin.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizkotlin.daos.QuestionDao
import com.example.quizkotlin.models.Question
import com.example.quizkotlin.models.Teacher

@Database(entities = arrayOf(Question::class), version = 1, exportSchema = false)
abstract class QuestionDatabase:RoomDatabase(){
    abstract fun questionDao() : QuestionDao
    companion object{
        @Volatile
        private var INSTANCE : QuestionDatabase?=null
        fun getInstance(context: Context) : QuestionDatabase?{
            if (INSTANCE == null){
                synchronized(QuestionDatabase::class){
                    INSTANCE = Room.databaseBuilder(context,
                        QuestionDatabase::class.java,
                        "question_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}