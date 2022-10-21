package com.king.easynote.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.king.easynote.presentation.DeleteNoteDialog
import com.king.easynote.presentation.NoteListScreen
import com.king.easynote.presentation.NoteScreen

/**
 * 笔记导航图
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
fun NavGraphBuilder.noteNavGraph(navController: NavController) {
    navigation(startDestination = Route.NoteListRoute.route, route = Route.NoteMain.route) {
        // 笔记列表
        composable(route = Route.NoteListRoute.route) {
            NoteListScreen(navController)
        }
        // 笔记 - 保存（修改/增加）
        composable(
            route = Route.NoteRoute.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            NoteScreen(navController = navController)
        }
        // 删除笔记对话框
        dialog(
            route = Route.DeleteNoteDialogRoute.route + "?noteId={noteId}",
            arguments = listOf(
                navArgument(name = "noteId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
            val noteId = it.arguments?.getInt("noteId") ?: -1
            DeleteNoteDialog(navController, noteId)
        }
    }

}