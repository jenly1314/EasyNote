package com.king.easynote.di

import android.app.Application
import androidx.room.Room
import com.king.easynote.data.datasource.NoteDatabase
import com.king.easynote.data.repository.NoteRepositoryImpl
import com.king.easynote.domain.repository.NoteRepository
import com.king.easynote.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 注入
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Singleton
    @Provides
    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases {
        return NoteUseCases(
            getNoteList = GetNoteListUseCase(repository),
            getNote = GetNoteUseCase(repository),
            saveNote = SaveNoteUseCase(repository),
            deleteNote = DeleteNoteUseCase(repository)
        )
    }
}