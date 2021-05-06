package com.example.quizkotlin.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizkotlin.constants.COLUMN_STUDENT_EMAIL
import com.example.quizkotlin.constants.COLUMN_STUDENT_ID
import com.example.quizkotlin.constants.COLUMN_STUDENT_PASSWORD
import com.example.quizkotlin.models.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM student ORDER BY $COLUMN_STUDENT_ID ASC")
    fun getAll() : LiveData<List<Student>>

    @Insert
    fun insertStudent(student: Student)

    @Query("SELECT $COLUMN_STUDENT_ID FROM student WHERE $COLUMN_STUDENT_EMAIL = :email")
    fun checkStudent(email : String) : Boolean

    @Query("SELECT $COLUMN_STUDENT_ID FROM student WHERE $COLUMN_STUDENT_EMAIL = :email AND $COLUMN_STUDENT_PASSWORD = :password")
    fun checkStudent(email: String, password : String) : Boolean
}