package com.example.schoolink.domain.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.dao.GroupStudentDao
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.relations.GroupWithStudent

class GroupStudentRepository(
    private val groupStudentDao: GroupStudentDao
) {
    suspend fun addStudentToGroup(crossRef: GroupStudentCrossRef){
        groupStudentDao.insertGroupStudentCrossRef(crossRef)
    }

    suspend fun getGroupWithStudents(groupId: Int): GroupWithStudent?{
        return groupStudentDao.getGroupWithStudents(groupId)
    }

    suspend fun removeStudentFromGroup(groupId: Int, studentId: Int){
        groupStudentDao.removeStudentFromGroup(groupId, studentId)
    }

    suspend fun removeAllStudentsFromGroup(groupId: Int) {
        groupStudentDao.removeAllStudentsFromGroup(groupId)
    }
}