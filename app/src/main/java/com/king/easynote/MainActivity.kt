package com.king.easynote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.king.easynote.base.ui.theme.EasyNoteTheme
import com.king.easynote.presentation.navigation.NavRoute
import com.king.easynote.presentation.navigation.noteNavGraph
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyNoteTheme {
                MainCompose()
            }
        }
    }
}

/**
 * 主页
 */
@Composable
fun MainCompose(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavRoute.NoteMain.route
        ) {
            noteNavGraph(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EasyNoteTheme {
        MainCompose()
    }
}