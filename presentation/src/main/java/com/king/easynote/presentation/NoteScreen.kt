package com.king.easynote.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.king.easynote.base.ui.theme.noteColors
import com.king.easynote.presentation.component.InputField
import com.king.easynote.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * 笔记 - 保存（修改/增加）
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@SuppressLint("UnrememberedAnimatable")
@Composable
fun NoteScreen(
    navController: NavController,
    viewModel: NoteViewModel = hiltViewModel()
) {

    val viewState = viewModel.state.value

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val backgroundAnim = Animatable(Color(viewState.color))

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(NoteViewModel.NoteEvent.SaveNote) },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(id = R.string.save)
                )
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                noteColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(16.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 2.dp,
                                color = if (viewState.color == colorInt) Color.White else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                scope.launch {
                                    backgroundAnim.animateTo(
                                        targetValue = color,
                                        animationSpec = tween()
                                    )
                                }
                                viewModel.onEvent(NoteViewModel.NoteEvent.ChangeColor(colorInt))
                            })

                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundAnim.value)
                .padding(16.dp)
        ) {
            // 标题
            InputField(
                value = viewState.title,
                onValueChange = {
                    viewModel.onEvent(NoteViewModel.NoteEvent.ChangeTitle(it))
                },
                hint = stringResource(id = R.string.hint_note_title),
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            )

            Spacer(modifier = Modifier.padding(10.dp))
            // 内容
            InputField(
                value = viewState.content,
                onValueChange = {
                    viewModel.onEvent(NoteViewModel.NoteEvent.ChangeContent(it))
                },
                hint = stringResource(id = R.string.hint_note_content),
                textStyle = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(bottom = it.calculateBottomPadding())
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.1f), RoundedCornerShape(32.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .align(Alignment.Start),
            )
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest {
            when (it) {
                is NoteViewModel.UIEvent.ShowMessage -> {
                    it.message?.run {
                        scaffoldState.snackbarHostState.showSnackbar(message = this)
                    }

                }
                is NoteViewModel.UIEvent.SaveNote -> {
                    navController.navigateUp()
                }
            }
        }
    }

}
