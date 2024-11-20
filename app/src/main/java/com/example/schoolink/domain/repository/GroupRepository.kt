package com.example.schoolink.domain.repository

import com.example.schoolink.data.dao.GroupDao
import com.example.schoolink.data.dao.GroupProfessorDao
import com.example.schoolink.data.dao.GroupStudentDao
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.relations.GroupWithProfessor
import com.example.schoolink.data.entities.relations.GroupWithStudents

class GroupRepository(
    private val groupDao: GroupDao,
    private val groupProfessorDao: GroupProfessorDao,
    private val groupStudentDao: GroupStudentDao
) {

    suspend fun createGroup(group: GroupEntity): Long {
        return groupDao.createGroup(group)
    }

    suspend fun linkGroupWithProfessor(groupId: Int, professorId: Int) {
        groupProfessorDao.insertGroupProfessorCrossRef(GroupProfessorCrossRef(groupId, professorId))
    }

    suspend fun linkGroupWithStudents(groupId: Int, studentIds: List<Int>) {
        studentIds.forEach { studentId ->
            groupStudentDao.insertGroupStudentCrossRef(GroupStudentCrossRef(groupId, studentId))
        }
    }

    suspend fun getGroupWithStudents(groupId: Int): GroupWithStudents? {
        return groupDao.getGroupWithStudents(groupId)
    }

    suspend fun getGroupWithProfessors(groupId: Int): GroupWithProfessor? {
        return groupDao.getGroupWithProfessor(groupId)
    }
}