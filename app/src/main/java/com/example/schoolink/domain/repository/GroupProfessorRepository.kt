package com.example.schoolink.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.dao.GroupProfessorDao
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.relations.GroupWithProfessor

class GroupProfessorRepository(
    private val groupProfessorDao: GroupProfessorDao
) {

    suspend fun addGroupToProfessor(crossRef: GroupProfessorCrossRef) {
        groupProfessorDao.insertGroupProfessorCrossRef(crossRef)
    }

    suspend fun getGroupsWithProfessor(professorId: Int): GroupWithProfessor? {
        return groupProfessorDao.getGroupsWithProfessor(professorId)
    }

    suspend fun removeGroupFromProfessor(groupId: Int, professorId: Int) {
        groupProfessorDao.removeGroupFromProfessor(groupId, professorId)
    }
}