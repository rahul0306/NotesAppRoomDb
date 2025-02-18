package com.example.notesapproomdb.presentation.notes_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.notesapproomdb.data.Notes
import com.example.notesapproomdb.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NotesScreen(
    navHostController: NavHostController,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: NotesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                containerColor = Color.Red,
                contentColor = Color.White,
                onClick = {
                    viewModel.onEvent(NotesListEvent.onAddNoteClick)
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            Column(modifier = modifier.padding(10.dp)) {
                Text(
                    text = "Your notes", style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                LazyColumn {
                    items(notes.value) { note ->
                        NoteListItem(notes = note,
                            onEvent = viewModel::onEvent,
                            modifier = modifier.clickable {
                                viewModel.onEvent(NotesListEvent.onNoteClick(note))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NoteListItem(
    notes: Notes,
    onEvent: (NotesListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
            .background(color = Color(0xFF252525))
            .padding(20.dp)
    ) {
        Image(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            modifier = modifier
                .align(
                    Alignment.TopEnd
                )
                .clickable {
                    onEvent(NotesListEvent.DeleteNote(notes))
                },
            colorFilter = ColorFilter.tint(Color.White)
        )
        Column {
            Text(
                text = notes.title, style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp
                )
            )
            notes.description?.let {
                Text(
                    text = it, style = TextStyle(
                        color = Color.LightGray
                    )
                )
            }

        }
    }
}

