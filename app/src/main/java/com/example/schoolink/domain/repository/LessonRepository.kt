package com.example.schoolink.domain.repository

import android.util.Log
import com.example.schoolink.data.dao.LessonDao
import com.example.schoolink.data.mappers.LessonMapper
import com.example.schoolink.domain.models.LessonModel

class LessonRepository(
    private val lessonDao: LessonDao
) {
    suspend fun createLesson(lessonModel: LessonModel): Long {
        return try{
            val entity = LessonMapper.fromModelToEntity(lessonModel)
            lessonDao.insertLesson(entity)
        } catch (e: Exception) {
            Log.e("LessonRepository", "Error inserting lesson", e)
            -1
        }
    }

    suspend fun getLessonById(lessonId: Int): LessonModel? {
        val entity = lessonDao.getLessonById(lessonId)
        return entity?.let { LessonMapper.fromEntityToModel(it) }
    }
}
