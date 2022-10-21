package com.king.easynote.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.king.easynote.base.ui.theme.ContentColor
import com.king.easynote.base.ui.theme.TitleColor
import com.king.easynote.domain.model.Note
import com.king.easynote.presentation.R

/**
 * 笔记 - 列表项
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 16.dp,
    cutCornerSize: Dp = 32.dp,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {

        // 绘制背景
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0F)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0F, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(ColorUtils.blendARGB(note.color, 0, 0.2F)),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -cutCornerSize.toPx()),
                    size = Size(cutCornerSize.toPx().times(2), cutCornerSize.toPx().times(2)),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                top = 16.dp,
                end = 32.dp,
                bottom = 16.dp
            )
        ) {
            // 标题
            Text(
                text = note.title,
                color = TitleColor,
                style = MaterialTheme.typography.h5,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.padding(6.dp))
            // 内容
            Text(
                text = note.content,
                color = ContentColor,
                style = MaterialTheme.typography.h6,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )

        }
        // 删除按钮
        IconButton(onClick = onDeleteClick, modifier = Modifier.align(Alignment.BottomEnd)) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete),
                tint = MaterialTheme.colors.onSurface
            )
        }

    }
}
