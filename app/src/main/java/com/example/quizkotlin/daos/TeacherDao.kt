package com.example.quizkotlin.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizkotlin.constants
import com.example.quizkotlin.models.Teacher

@Dao
interface TeacherDao {
    @Query("SELECT * FROM teacher ORDER BY ${constants.COLUMN_TEACHER_ID} ASC")
    fun getAll() : LiveData<List<Teacher>>

    @Insert
    fun insertTeacher(teacher: Teacher)

    @Query("SELECT ${constants.COLUMN_TEACHER_ID} FROM teacher WHERE ${constants.COLUMN_TEACHER_EMAIL} = :email")
    fun checkTeacher(email : String) : Boolean

    @Query("SELECT ${constants.COLUMN_TEACHER_ID} FROM teacher WHERE ${constants.COLUMN_TEACHER_EMAIL} = :email AND ${constants.COLUMN_TEACHER_PASSWORD} = :password")
    fun checkTeacher(email: String, password : String) : Boolean
}