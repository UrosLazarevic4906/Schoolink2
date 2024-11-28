package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.refference.LessonProfessorCrossRef
import com.example.schoolink.data.entities.relations.LessonWithProfessor
import com.example.schoolink.domain.repository.LessonProfessorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonProfessorViewModel (
    private val repository: LessonProfessorRepository
) : ViewModel(){

    fun addLessonToProfessor(lessonId: Int, professorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLessonToProfessor(LessonProfessorCrossRef(lessonId, professorId))
        }
    }

    fun getLessonsWithProfessor(professorId: Int, onResult: (LessonWithProfessor?) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getLessonsWithProfessor(professorId)
            }
            onResult(result)
        }
    }

    fun removeLessonFromProfessor(lessonId: Int, professorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeLessonFromProfessor(lessonId, professorId)
        }
    }
}