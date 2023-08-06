package com.example.jetnotes.data.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.jetnotes.data.NoteDao
import com.example.jetnotes.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun getAllNotes(): Flow<List<Note>> = noteDao.getAllNotes().flowOn(Dispatchers.IO).conflate()

    suspend fun getNoteById(id: String) : Note = noteDao.getNoteById(id)

    suspend fun addNote(note: Note)  = noteDao.addNote(note)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

    suspend fun deleteNote(note: Note) = noteDao.deleteNote(note)

    suspend fun deleteAll() = noteDao.deleteAll()

}