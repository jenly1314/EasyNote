package com.king.easynote.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 笔记
 *
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@Entity
data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
)