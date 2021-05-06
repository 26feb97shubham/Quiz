package com.example.quizkotlin.repositories

import android.app.Application
import com.example.quizkotlin.daos.StudentDao
import com.example.quizkotlin.daos.TeacherDao
import com.example.quizkotlin.databases.StudentDatabase
import com.example.quizkotlin.databases.TeacherDatabase
import com.example.quizkotlin.models.Student
import com.example.quizkotlin.models.Teacher
import kotlinx.coroutines.coroutineScope

class TeacherRepository(application: Application?) {
    private val teacherDao : TeacherDao

    init {
        val database = application?.let { TeacherDatabase.getInstance(it.applicationContext) }
        teacherDao = database!!.teacherDao()
    }

    fun insertTeacher(teacher: Teacher){
            teacherDao.insertTeacher(teacher)
    }

    fun checkTeacher(email : String) : Boolean{
        var ispresent : Boolean = false
            if (teacherDao.checkTeacher(email)!=null){
                ispresent = true
            }else{
                ispresent = false
            }
        return ispresent
    }

    fun checkTeacher(email : String, password : String): Boolean {
        var ispresent : Boolean = false
            if(teacherDao.checkTeacher(email,password)!=null){
                ispresent = true
            }else{
                ispresent = false
            }
        return ispresent
    }
}