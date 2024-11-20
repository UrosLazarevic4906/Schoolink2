package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.relations.GroupWithProfessor
import com.example.schoolink.data.entities.relations.GroupWithStudents

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createGroup(group: GroupEntity): Long

    @Transaction
    @Query("SELECT * FROM groups WHERE groupId = :groupId")
    suspend fun getGroupWithProfessor(groupId: Int): GroupWithProfessor?

    @Transaction
    @Query("SELECT * FROM groups WHERE groupId = :groupId")
    suspend fun getGroupWithStudents(groupId: Int): GroupWithStudents?

    @Query("SELECT * FROM groups where groupId = :groupId")
    suspend fun getGroup(groupId: Int): GroupEntity
}