package com.king.easynote.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.DialogProperties
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
    navigation(
        startDestination = NavRoute.NoteListRoute.navGraphRoute(),
        route = NavRoute.NoteMain.route
    ) {
        // 笔记列表
        composable(navRoute = NavRoute.NoteListRoute) {
            NoteListScreen(navController)
        }
        // 笔记 - 保存（修改/增加）
        composable(navRoute = NavRoute.NoteRoute) {
            NoteScreen(navController = navController)
        }
        // 删除笔记对话框
        dialog(navRoute = NavRoute.DeleteNoteDialogRoute) {
            val noteId = it.arguments?.getInt(NavArgumentKey.NoteId.name) ?: NavRoute.NONE_ID
            DeleteNoteDialog(navController, noteId)
        }
    }

}

/**
 * 将 Composable 添加到导航图
 */
fun NavGraphBuilder.composable(
    navRoute: NavRoute,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) = composable(
    route = navRoute.navGraphRoute(),
    arguments = navRoute.arguments,
    deepLinks = deepLinks,
    content = content
)

/**
 * 将 Composable 以对话框的方式添加到导航图
 */
fun NavGraphBuilder.dialog(
    navRoute: NavRoute,
    deepLinks: List<NavDeepLink> = emptyList(),
    dialogProperties: DialogProperties = DialogProperties(),
    content: @Composable (NavBackStackEntry) -> Unit
) = dialog(
    route = navRoute.navGraphRoute(),
    arguments = navRoute.arguments,
    dialogProperties = dialogProperties,
    deepLinks = deepLinks,
    content = content
)

