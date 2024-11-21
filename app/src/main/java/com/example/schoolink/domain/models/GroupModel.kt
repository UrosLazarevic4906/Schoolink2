package com.example.schoolink.domain.models

data class GroupModel(
    val groupId: Int = 0,
    val groupName: String,
    val groupPicturePath: String? = null,
    val groupType: GroupType? = null,
)
