package com.example.notesapproomdb.data

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class NoteRepositoryImpl(
    private val dao: NoteDao,
    private val firestore: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(): Flow<List<Notes>> {
        return dao.getNotes()
    }

    override suspend fun getNoteById(id: String): Notes? {
        return dao.getNoteById(id)
    }

    override suspend fun insertNote(notes: Notes) {
        try {
            val docRef = firestore.collection("notes").document()
            val noteWithId = notes.copy(id = docRef.id)

            docRef.set(noteWithId).await()
            dao.insertNote(noteWithId)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteNote(notes: Notes) {
        try {
            if (notes.id.isNotEmpty()) {
                firestore.collection("notes")
                    .document(notes.id)
                    .delete()
                    .await()
            }
            dao.deleteNote(notes)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}