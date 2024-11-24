package com.example.schoolink.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.dao.GroupProfessorDao
import com.example.schoolink.data.dao.GroupStudentDao
import com.example.schoolink.data.dao.LessonDao
import com.example.schoolink.data.dao.LessonGroupDao
import com.example.schoolink.data.dao.LessonProfessorDao
import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.dao.ProfessorStudentDao
import com.example.schoolink.data.dao.StudentDao
import com.example.schoolink.data.database.converters.DateTimeConverters
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.data.database.converters.GenderTypeConverter
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.LessonEntity
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.refference.LessonGroupCrossRef
import com.example.schoolink.data.entities.refference.LessonProfessorCrossRef
import com.example.schoolink.data.entities.refference.ProfessorStudentCrossRef

@Database(
    entities = [
        ProfessorEntity::class,
        StudentEntity::class,
        ProfessorStudentCrossRef::class,
        GroupEntity::class,
        GroupProfessorCrossRef::class,
        GroupStudentCrossRef::class,
        LessonEntity::class,
        LessonProfessorCrossRef::class,
        LessonGroupCrossRef::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(GenderTypeConverter::class, DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun professorDao(): ProfessorDao
    abstract fun studentDao(): StudentDao
    abstract fun professorStudentDao(): ProfessorStudentDao
    abstract fun groupDao(): GroupDao
    abstract fun groupProfessorDao(): GroupProfessorDao
    abstract fun groupStudentDao(): GroupStudentDao
    abstract fun lessonDao(): LessonDao
    abstract fun lessonProfessorDao(): LessonProfessorDao
    abstract fun lessonGroupDao(): LessonGroupDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sc1-db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
