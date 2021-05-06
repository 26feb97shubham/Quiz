package com.example.quizkotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizkotlin.constants.COLUMN_STUDENT_EMAIL
import com.example.quizkotlin.constants.COLUMN_STUDENT_ID
import com.example.quizkotlin.constants.COLUMN_STUDENT_NAME
import com.example.quizkotlin.constants.COLUMN_STUDENT_PASSWORD
import com.example.quizkotlin.constants.STUDENT_TABLE

@Entity(tableName = STUDENT_TABLE)
data class Student(
    @ColumnInfo(name = COLUMN_STUDENT_ID)
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = COLUMN_STUDENT_NAME)
    var name : String,
    @ColumnInfo(name = COLUMN_STUDENT_EMAIL)
    var email : String,
    @ColumnInfo(name = COLUMN_STUDENT_PASSWORD)
    var password : String
)
