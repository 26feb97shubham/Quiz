package com.example.quizkotlin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizkotlin.constants.COLUMN_TEACHER_EMAIL
import com.example.quizkotlin.constants.COLUMN_TEACHER_ID
import com.example.quizkotlin.constants.COLUMN_TEACHER_NAME
import com.example.quizkotlin.constants.COLUMN_TEACHER_PASSWORD
import com.example.quizkotlin.constants.TEACHER_TABLE

@Entity(tableName = TEACHER_TABLE)
data class Teacher(
    @ColumnInfo(name = COLUMN_TEACHER_ID)
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name = COLUMN_TEACHER_NAME)
    var name : String,
    @ColumnInfo(name = COLUMN_TEACHER_EMAIL)
    var email : String,
    @ColumnInfo(name = COLUMN_TEACHER_PASSWORD)
    var password : String
)
