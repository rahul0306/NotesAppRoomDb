package com.example.notesapproomdb.presentation.insert_note_screen.splash_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.notesapproomdb.R
import com.example.notesapproomdb.util.Routes
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        delay(2000)
        navHostController.navigate(route = Routes.NOTES_LIST)
    }
    Scaffold {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Black)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}
