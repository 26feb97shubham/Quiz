package com.example.quizkotlin.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizkotlin.constants.COLUMN_ANSWER
import com.example.quizkotlin.constants.COLUMN_OPTION_A
import com.example.quizkotlin.constants.COLUMN_OPTION_B
import com.example.quizkotlin.constants.COLUMN_OPTION_C
import com.example.quizkotlin.constants.COLUMN_OPTION_D
import com.example.quizkotlin.constants.COLUMN_QUESTION
import com.example.quizkotlin.constants.COLUMN_QUESTION_ID
import com.example.quizkotlin.constants.COLUMN_QUESTION_SET
import com.example.quizkotlin.constants.COLUMN_SUBJECT_NAME
import com.example.quizkotlin.constants.QUESTIONS_TABLE
import kotlinx.android.parcel.Parcelize

@Entity(tableName = QUESTIONS_TABLE)
@Parcelize()
data class Question(
    @ColumnInfo(name = COLUMN_QUESTION_ID)
    @PrimaryKey(autoGenerate = true)
    var questionId : Int,
    @ColumnInfo(name = COLUMN_SUBJECT_NAME)
    var subjectName : String,
    @ColumnInfo(name = COLUMN_QUESTION_SET)
    var questionSet : String,
    @ColumnInfo(name = COLUMN_QUESTION)
    var question : String,
    @ColumnInfo(name = COLUMN_OPTION_A)
    var optionA : String,
    @ColumnInfo(name = COLUMN_OPTION_B)
    var optionB : String,
    @ColumnInfo(name = COLUMN_OPTION_C)
    var optionC : String,
    @ColumnInfo(name = COLUMN_OPTION_D)
    var optionD : String,
    @ColumnInfo(name = COLUMN_ANSWER)
    var correctAnswer : String
) : Parcelable
