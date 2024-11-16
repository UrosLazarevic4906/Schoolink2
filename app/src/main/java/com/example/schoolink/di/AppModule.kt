package com.example.schoolink.di

import android.content.Context
import androidx.room.Room
import com.example.schoolink.data.database.AppDatabase
import com.example.schoolink.domain.repository.ProfessorRepository
import com.example.schoolink.ui.viewmodels.ProfessorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get<Context>(),
            AppDatabase::class.java,
            "schoolink-db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().professorDao() }

    single { ProfessorRepository(get()) }

    viewModel { ProfessorViewModel(get()) }
}
