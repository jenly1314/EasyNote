package com.king.easynote.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.king.easynote.presentation.viewmodel.DeleteNoteViewModel

/**
 * 删除笔记对话框
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Composable
fun DeleteNoteDialog(
    navController: NavController,
    noteId: Int,
    viewModel: DeleteNoteViewModel = hiltViewModel()
) {

    AlertDialog(
        modifier = Modifier
            .background(color = Color.White, RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp)),
        onDismissRequest = { navController.popBackStack() },
        title = {
            Text(
                text = stringResource(id = R.string.delete_note_dialog_title),
                style = MaterialTheme.typography.h6
            )
        },
        text = {
            Text(
                text = stringResource(id = R.string.delete_note_dialog_content),
                style = MaterialTheme.typography.h6
            )
        },
        confirmButton = {
            Text(
                text = stringResource(id = R.string.delete_note_dialog_confirm),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clickable {
                        // 删除笔记
                        viewModel.onEvent(DeleteNoteViewModel.DeleteNoteEvent.DeleteNote(noteId))
                        navController.popBackStack()
                    }
            )

        },
        dismissButton = {
            Text(
                text = stringResource(id = R.string.delete_note_dialog_cancel),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(all = 10.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        },
    )
}