package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.domain.models.GroupModel

class GroupRepository(
    private val groupDao: GroupDao
) {
    suspend fun createGroup(groupModel: GroupModel) {
        val entity = GroupMapper.fromModelToEntity(groupModel)
        groupDao.insertGroup(entity)
    }

    suspend fun getGroupById(groupId: Int): GroupModel? {
        val entity = groupDao.getGroupById(groupId)
        return entity?.let { GroupMapper.fromEntityToModel(it) }
    }
}
