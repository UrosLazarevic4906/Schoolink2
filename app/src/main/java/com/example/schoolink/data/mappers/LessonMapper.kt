package com.example.schoolink.data.mappers

import com.example.schoolink.data.entities.LessonEntity
import com.example.schoolink.domain.models.LessonModel

object LessonMapper {

    fun fromEntityToModel(entity: LessonEntity): LessonModel{
        return LessonModel(
            lessonId = entity.lessonId,
            date = entity.date,
            startTime = entity.startTime,
            endTime = entity.endTime
        )
    }

    fun fromModelToEntity(model: LessonModel): LessonEntity {
        return LessonEntity(
            lessonId = model.lessonId,
            date = model.date,
            startTime = model.startTime,
            endTime = model.endTime
        )
    }
}