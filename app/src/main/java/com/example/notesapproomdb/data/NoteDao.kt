package com.example.notesapproomdb.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM Notes")
    fun getNotes():Flow<List<Notes>>

    @Query("SELECT * FROM NOTES WHERE id= :id")
    suspend fun getNoteById(id: Int): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)
}