package com.example.schoolink.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.dao.StudentDao
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.data.database.converters.GenderTypeConverter
import com.example.schoolink.data.entities.ProfessorStudentCrossRef

@Database(
    entities = [ProfessorEntity::class, StudentEntity::class, ProfessorStudentCrossRef::class],
    version = 4,
    exportSchema = false
)
@TypeConverters(GenderTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun professorDao(): ProfessorDao
    abstract fun studentDao(): StudentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "schoolink-db"
                ).
                fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
