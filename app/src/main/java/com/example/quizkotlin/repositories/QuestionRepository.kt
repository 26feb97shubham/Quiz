package com.example.quizkotlin.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.quizkotlin.daos.QuestionDao
import com.example.quizkotlin.databases.QuestionDatabase
import com.example.quizkotlin.databases.StudentDatabase
import com.example.quizkotlin.models.Question
import kotlinx.coroutines.coroutineScope

class QuestionRepository(application: Application?) {
    private val questionDao : QuestionDao
    private val getallquestionsList : LiveData<List<Question>>
    init {
        val database = application?.let { QuestionDatabase.getInstance(it.applicationContext) }
        questionDao = database!!.questionDao()
        getallquestionsList = questionDao.getAllQuestions()
    }

    fun insertQuestions(question: Question){
        questionDao.insertQuestion(question)
    }

    fun getAllQuestions() : LiveData<List<Question>>{
        return getAllQuestions()
    }
}