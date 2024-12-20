package com.example.notesapproomdb.data

import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotes():Flow<List<Notes>>

    suspend fun getNoteById(id:String): Notes?

    suspend fun insertNote(notes: Notes)

    suspend fun deleteNote(notes: Notes)
}