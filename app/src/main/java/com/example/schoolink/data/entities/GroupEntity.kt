package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.schoolink.domain.models.GroupType

@Entity(
    tableName = "groups",
)
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val groupId: Int = 0,
    val groupName: String,
    val groupType: GroupType,
    val groupPicturePath: String?
)