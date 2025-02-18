package com.example.notesapproomdb.presentation.notes_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapproomdb.data.NoteRepository
import com.example.notesapproomdb.util.Routes
import com.example.notesapproomdb.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val notes = repository.getNotes()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: NotesListEvent) {
        when (event) {
            is NotesListEvent.DeleteNote -> {
                viewModelScope.launch {
                    repository.deleteNote(event.notes)
                }
            }

            is NotesListEvent.onAddNoteClick -> {
                sendUiEvent(UiEvent.Navigate(Routes.INSERT_NOTE))
            }

            is NotesListEvent.onNoteClick -> {
                 sendUiEvent(UiEvent.Navigate(Routes.INSERT_NOTE + "?noteId=${event.notes.id}"))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}