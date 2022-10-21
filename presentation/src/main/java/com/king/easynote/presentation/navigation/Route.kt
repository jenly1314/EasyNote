package com.king.easynote.presentation.navigation

/**
 * 路由
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
sealed class Route(val route: String) {

    object NoteMain: Route("MoteMain")

    object NoteListRoute: Route("NoteListScreen")

    object DeleteNoteDialogRoute: Route("DeleteNoteDialogRoute")

    object NoteRoute: Route("NoteScreen")
}