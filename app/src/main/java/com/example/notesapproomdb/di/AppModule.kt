package com.example.notesapproomdb.di

import android.app.Application
import androidx.room.Room
import com.example.notesapproomdb.data.NoteDatabase
import com.example.notesapproomdb.data.NoteDatabase.Companion.MIGRATION_1_2
import com.example.notesapproomdb.data.NoteRepository
import com.example.notesapproomdb.data.NoteRepositoryImpl
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "note_db"
        ).addMigrations(MIGRATION_1_2).build()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(
        db: NoteDatabase,
        firestore: FirebaseFirestore
    ): NoteRepository {
        return NoteRepositoryImpl(db.dao, firestore)
    }
}