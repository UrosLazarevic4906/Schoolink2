package com.example.schoolink.domain.repository

import android.util.Log
import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.mappers.GroupMapper
import com.example.schoolink.domain.models.GroupModel

class GroupRepository(
    private val groupDao: GroupDao
) {
    suspend fun createGroup(groupModel: GroupModel): Long {
        return try {
            val entity = GroupMapper.fromModelToEntity(groupModel)
            groupDao.insertGroup(entity)
        } catch (e: Exception) {
            Log.e("GroupRepository", "Error inserting group", e)
            -1 // Return invalid ID to signal failure
        }
    }


    suspend fun getGroupById(groupId: Int): GroupModel? {
        val entity = groupDao.getGroupById(groupId)
        return entity?.let { GroupMapper.fromEntityToModel(it) }
    }
}
