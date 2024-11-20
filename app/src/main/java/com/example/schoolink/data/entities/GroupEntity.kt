package com.example.schoolink.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val groupId: Int = 0,
    val groupName: String,
    val groupType: String,
    val groupPicturePath: String?
)
