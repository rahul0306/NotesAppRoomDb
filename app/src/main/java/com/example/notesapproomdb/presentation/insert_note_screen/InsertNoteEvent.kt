package com.example.notesapproomdb.presentation.insert_note_screen

sealed class InsertNoteEvent {
    data class onTitleChange(val title:String):InsertNoteEvent()
    data class onDescriptionChange(val description:String):InsertNoteEvent()
    object onSaveNoteClick:InsertNoteEvent()
}