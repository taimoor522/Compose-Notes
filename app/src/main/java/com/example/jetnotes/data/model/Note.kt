package com.example.jetnotes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.jetnotes.data.DateConverter
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes")
data class Note(

    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "body")
    val body: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Date = Calendar.getInstance().time
)
