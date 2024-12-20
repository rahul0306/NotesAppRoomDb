package com.example.notesapproomdb.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notesapproomdb.presentation.insert_note_screen.InsertNoteScreen
import com.example.notesapproomdb.presentation.insert_note_screen.notes_screen.NotesScreen
import com.example.notesapproomdb.presentation.insert_note_screen.splash_screen.SplashScreen
import com.example.notesapproomdb.util.Routes

@Composable
fun NotesNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SPLASH_SCREEN){
        composable(route = Routes.SPLASH_SCREEN){
            SplashScreen(navHostController = navController)
        }
        composable(route = Routes.NOTES_LIST){
            NotesScreen(navHostController = navController,
                onNavigate = {navController.navigate(it.route)})
        }
        composable(route =Routes.INSERT_NOTE + "?noteId={noteId}",
            arguments = listOf(
                navArgument(
                    name = "noteId"
                ){
                    type = NavType.StringType
                    defaultValue = null
                    nullable=true
                }
            )
        ){
            InsertNoteScreen(onPopBackStack = {
                navController.popBackStack()
            })
        }
    }


}