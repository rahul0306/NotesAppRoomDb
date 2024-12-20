package com.example.notesapproomdb.presentation.insert_note_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapproomdb.data.NoteRepository
import com.example.notesapproomdb.data.Notes
import com.example.notesapproomdb.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InsertNoteViewModel @Inject constructor(
    private val repository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var note by mutableStateOf<Notes?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val noteId = savedStateHandle.get<String>("noteId")
        if (noteId != null) {
            if (noteId != "-1" && noteId.isNotEmpty()) {
                viewModelScope.launch {
                    repository.getNoteById(noteId)?.let { note ->
                        title = note.title
                        description = note.description ?: ""
                        this@InsertNoteViewModel.note = note
                    }
                }
            }
        }
    }

    fun onEvent(event: InsertNoteEvent) {
        when (event) {
            is InsertNoteEvent.onDescriptionChange -> {
                description = event.description
            }

            is InsertNoteEvent.onSaveNoteClick -> {
                viewModelScope.launch {
                    repository.insertNote(
                        Notes(
                            title = title,
                            description = description,
                            id = note?.id ?:"0"
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }

            is InsertNoteEvent.onTitleChange -> {
                title = event.title
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}