package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.schoolink.data.entities.GroupEntity
import com.example.schoolink.data.entities.ProfessorEntity

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroup(group: GroupEntity): Long

    @Update
    suspend fun updateGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)

    @Query("SELECT * FROM groups WHERE groupId = :groupId")
    suspend fun getGroupById(groupId: Int): GroupEntity?
}