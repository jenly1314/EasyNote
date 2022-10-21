package com.king.easynote.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.king.easynote.base.ui.theme.HintColor
import com.king.easynote.base.ui.theme.TitleColor
import com.king.easynote.presentation.component.InputField
import com.king.easynote.presentation.component.NoteItem
import com.king.easynote.presentation.navigation.Route
import com.king.easynote.presentation.viewmodel.NoteListViewModel


/**
 * 笔记列表
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel(
        navController.getBackStackEntry(Route.NoteListRoute.route)
    )
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Route.NoteRoute.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add)
                )
            }
        }

    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            TopBar()
            Spacer(modifier = Modifier.padding(10.dp))
            NoteListContent(navController, viewModel)
        }
    }

}

/**
 * 顶部标题栏
 */
@Composable
private fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.note_title),
            color = TitleColor,
            style = MaterialTheme.typography.h5
        )
    }

}

/**
 * 笔记列表内容
 */
@Composable
fun NoteListContent(
    navController: NavController,
    viewModel: NoteListViewModel
) {
    val state = viewModel.state
    val noteList = state.value.notes
    // 搜索框
    InputField(
        value = state.value.text,
        onValueChange = {
            viewModel.onEvent(NoteListViewModel.NoteListEvent.SearchNote(it))
        },
        hint = stringResource(id = R.string.hint_note_search),
        singleLine = true,
        textStyle = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
    )

    if (noteList.isNotEmpty()) {
        Spacer(modifier = Modifier.padding(10.dp))
        // 列表项
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(noteList) { note ->
                NoteItem(
                    note = note,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            // 修改笔记
                            navController.navigate(Route.NoteRoute.route + "?noteId=${note.id}")
                        }) {
                    // 点击删除笔记
                    navController.navigate(Route.DeleteNoteDialogRoute.route + "?noteId=${note.id}")
                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }
            // 让底部能向上滑一段距离，避免被遮盖
            item {
                Spacer(modifier = Modifier.padding(bottom = 56.dp))
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = stringResource(R.string.content_is_empty), color = HintColor)
        }
    }

}

