package com.example.notesapproomdb.presentation.insert_note_screen.notes_screen

import com.example.notesapproomdb.data.Notes

sealed class NotesListEvent {

    data class DeleteNote(val notes: Notes):NotesListEvent()
    data class onNoteClick(val notes: Notes):NotesListEvent()
    object onAddNoteClick:NotesListEvent()
}