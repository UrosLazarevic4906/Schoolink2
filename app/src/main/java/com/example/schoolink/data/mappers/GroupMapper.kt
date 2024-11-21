package com.example.schoolink.data.mappers

import androidx.compose.ui.graphics.vector.Group
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.domain.models.GroupModel

object GroupMapper {

    fun fromEntityToModel(entity: GroupEntity): GroupModel {
        return GroupModel(
            groupId = entity.groupId,
            groupName = entity.groupName,
            groupType = entity.groupType,
            groupPicturePath = entity.groupPicturePath
        )
    }

    fun fromModelToEntity(model: GroupModel): GroupEntity {
        return GroupEntity(
            groupId = model.groupId,
            groupName = model.groupName,
            groupType = model.groupType,
            groupPicturePath = model.groupPicturePath
        )
    }
}