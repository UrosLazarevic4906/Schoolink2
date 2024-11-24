package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.LessonProfessorDao
import com.example.schoolink.data.entities.refference.LessonProfessorCrossRef
import com.example.schoolink.data.entities.relations.LessonWithProfessor

class LessonProfessorRepository(
    private val lessonProfessorDao: LessonProfessorDao
) {

    suspend fun addLessonToProfessor(crossRef: LessonProfessorCrossRef) {
        lessonProfessorDao.insertLessonProfessorCrossRef(crossRef)
    }

    suspend fun getLessonsWithProfessor(professorId: Int): LessonWithProfessor? {
        return lessonProfessorDao.getLessonsWithProfessor(professorId)
    }

    suspend fun removeLessonFromProfessor(lessonId: Int, professorId: Int) {
        lessonProfessorDao.removeLesonFromProfessor(lessonId, professorId)
    }
}