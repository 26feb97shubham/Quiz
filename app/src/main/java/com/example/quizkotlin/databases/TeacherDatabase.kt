package com.example.quizkotlin.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizkotlin.daos.StudentDao
import com.example.quizkotlin.daos.TeacherDao
import com.example.quizkotlin.models.Student
import com.example.quizkotlin.models.Teacher

@Database(entities = arrayOf(Teacher::class), version = 1)
abstract class TeacherDatabase : RoomDatabase() {
    abstract fun teacherDao() : TeacherDao
    companion object{
        @Volatile
        private var INSTANCE : TeacherDatabase?=null
        fun getInstance(context: Context) : TeacherDatabase?{
            if (INSTANCE == null){
                synchronized(TeacherDatabase::class){
                    INSTANCE = Room.databaseBuilder(context,
                        TeacherDatabase::class.java,
                        "teacher_db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}