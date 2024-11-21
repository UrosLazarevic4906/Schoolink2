package com.example.schoolink.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.dao.GroupProfessorDao
import com.example.schoolink.data.dao.GroupStudentDao
import com.example.schoolink.data.dao.ProfessorDao
import com.example.schoolink.data.dao.ProfessorStudentDao
import com.example.schoolink.data.dao.StudentDao
import com.example.schoolink.data.entities.ProfessorEntity
import com.example.schoolink.data.entities.StudentEntity
import com.example.schoolink.data.database.converters.GenderTypeConverter
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.refference.ProfessorStudentCrossRef

@Database(
    entities = [
        ProfessorEntity::class,
        StudentEntity::class,
        ProfessorStudentCrossRef::class,
        GroupEntity::class,
        GroupProfessorCrossRef::class,
        GroupStudentCrossRef::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenderTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun professorDao(): ProfessorDao
    abstract fun studentDao(): StudentDao
    abstract fun professorStudentDao(): ProfessorStudentDao
    abstract fun groupDao(): GroupDao
    abstract fun groupProfessorDao(): GroupProfessorDao
    abstract fun groupStudentDao(): GroupStudentDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "schuolinko-db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
