package com.example.schoolink.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.domain.models.LessonModel
import com.example.schoolink.domain.repository.LessonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LessonViewModel(
    private val repository: LessonRepository
) : ViewModel(){
    var currentLesson: LessonModel? = null

    fun createLesson(lesson: LessonModel, onLessonCreated: (Long) -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            try{
                val lessonId = repository.createLesson(lesson)
                withContext(Dispatchers.Main) {
                    if(lessonId> 0) {
                        onLessonCreated(lessonId)
                    } else {
                        throw Exception("Lesson creation failed, invalid Id: $lessonId")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("LessonViewModel", "Error creating lesson", e)
                    throw e
                }
            }
        }
    }

    fun getLessonById(lessonId: Int, onResult: (LessonModel?) -> Unit) {
        viewModelScope.launch {
            val lesson = withContext(Dispatchers.IO) {
                repository.getLessonById(lessonId)
            }
            currentLesson = lesson
            onResult(lesson)
        }
    }
}