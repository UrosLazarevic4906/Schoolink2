package com.example.schoolink.domain.repository

import android.util.Log
import androidx.compose.ui.graphics.vector.Group
import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.data.mappers.ProfessorMapper
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.ProfessorModel

class GroupRepository(
    private val groupDao: GroupDao
) {
    suspend fun createGroup(groupModel: GroupModel): Long {
        return try {
            val entity = GroupMapper.fromModelToEntity(groupModel)
            groupDao.insertGroup(entity)
        } catch (e: Exception) {
            Log.e("GroupRepository", "Error inserting group", e)
            -1
        }
    }

    suspend fun deleteGroup(groupModel: GroupModel) {
        val entity = GroupMapper.fromModelToEntity(groupModel)
        groupDao.deleteGroup(entity)
    }

    suspend fun updateGroup(groupModel: GroupModel) {
        val entity = GroupMapper.fromModelToEntity(groupModel)
        groupDao.updateGroup(entity)
    }

    suspend fun getGroupById(groupId: Int): GroupModel? {
        val entity = groupDao.getGroupById(groupId)
        return entity?.let { GroupMapper.fromEntityToModel(it) }
    }
}
