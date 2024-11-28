package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.data.entities.refference.LessonGroupCrossRef
import com.example.schoolink.data.entities.relations.LessonWithGroup
import com.example.schoolink.domain.repository.LessonGroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonGroupViewModel(
    private val repository: LessonGroupRepository
) : ViewModel() {

    fun addGroupToLesson(lessonId: Int, groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGroupToLesson(LessonGroupCrossRef(lessonId, groupId))
        }
    }

    fun getLessonWithGroup(lessonId: Int, onResult: (LessonWithGroup?) -> Unit) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getLessonWithGroup(lessonId)
            }
            onResult(result)
        }
    }

    fun removeLessonWithGroup(lessonId: Int, groupId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeLessonWithGroup(lessonId, groupId)
        }
    }
}
