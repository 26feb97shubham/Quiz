package com.example.quizkotlin.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizkotlin.daos.StudentDao
import com.example.quizkotlin.models.Student

@Database(entities = arrayOf(Student::class), version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {
    abstract fun studentDao() : StudentDao
    companion object{
        @Volatile
        private var INSTANCE : StudentDatabase?=null
        fun getInstance(context: Context) : StudentDatabase?{
            if (INSTANCE == null){
                synchronized(StudentDatabase::class){
                    INSTANCE = Room.databaseBuilder(context,
                    StudentDatabase::class.java,
                    "student_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}