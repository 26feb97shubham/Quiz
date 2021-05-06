package com.example.quizkotlin.repositories

import android.app.Application
import com.example.quizkotlin.daos.StudentDao
import com.example.quizkotlin.databases.StudentDatabase
import com.example.quizkotlin.models.Student
import kotlinx.coroutines.coroutineScope

class StudentRepository(application: Application?) {
    private val studentDao : StudentDao

    init {
        val database = application?.let { StudentDatabase.getInstance(it.applicationContext) }
        studentDao = database!!.studentDao()
    }

    fun insertStudent(student: Student){
        studentDao.insertStudent(student)
    }

    fun checkStudent(email : String) : Boolean{
        var ispresent  :Boolean = false
            if(studentDao.checkStudent(email)){
                ispresent = true
            }else{
                ispresent = false
            }
        return ispresent
    }

    fun checkStudent(email : String, password : String): Boolean {
        var ispresent  :Boolean = false
            if(studentDao.checkStudent(email,password)){
                ispresent = true
            }else{
                ispresent = false
            }
        return ispresent
    }
}