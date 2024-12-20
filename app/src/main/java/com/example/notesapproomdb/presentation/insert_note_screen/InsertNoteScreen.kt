package com.example.notesapproomdb.presentation.insert_note_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notesapproomdb.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InsertNoteScreen(
    onPopBackStack: () -> Unit,
    viewModel: InsertNoteViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> Unit
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(shape = RoundedCornerShape(corner = CornerSize(100.dp)),
                containerColor = Color.Red,
                contentColor = Color.White,
                onClick = {
                    viewModel.onEvent(InsertNoteEvent.onSaveNoteClick)
                }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Done")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black)
        ) {
            Column(modifier = modifier.padding(15.dp)) {
                Text(
                    text = "Add Note", style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    label = {
                        Text(
                            text = "Enter title", style = TextStyle(
                                color = Color.White
                            )
                        )
                    },
                    value = viewModel.title,
                    onValueChange = {
                        viewModel.onEvent(InsertNoteEvent.onTitleChange(it))
                    },
                    modifier = modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.DarkGray,
                        focusedContainerColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.LightGray,
                        unfocusedTextColor = Color.LightGray
                    )
                )
                Spacer(modifier = modifier.height(10.dp))
                TextField(
                    label = {
                        Text(
                            text = "Enter Description", style = TextStyle(
                                color = Color.White
                            )
                        )
                    },
                    value = viewModel.description,
                    onValueChange = {
                        viewModel.onEvent(InsertNoteEvent.onDescriptionChange(it))
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f),
                    shape = RoundedCornerShape(corner = CornerSize(10.dp)),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.DarkGray,
                        focusedContainerColor = Color.DarkGray,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.LightGray,
                        unfocusedTextColor = Color.LightGray
                    )
                )
            }
        }

    }
}