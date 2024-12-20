package com.example.notesapproomdb.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val dao: NoteDao
}