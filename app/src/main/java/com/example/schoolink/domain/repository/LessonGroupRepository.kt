package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.LessonGroupDao
import com.example.schoolink.data.entities.refference.LessonGroupCrossRef
import com.example.schoolink.data.entities.relations.LessonWithGroup

class LessonGroupRepository (
    private val lessonGroupDao: LessonGroupDao
){

    suspend fun addGroupToLesson(crossRef: LessonGroupCrossRef) {
        lessonGroupDao.insertLessonGroupCrossRef(crossRef)
    }

    suspend fun getLessonWithGroup(lessonId: Int): LessonWithGroup? {
        return lessonGroupDao.getLessonWithGroup(lessonId)
    }

    suspend fun removeLessonWithGroup(lessonId: Int, groupId: Int) {
        lessonGroupDao.removeLessonWithGroup(lessonId, groupId)
    }
}