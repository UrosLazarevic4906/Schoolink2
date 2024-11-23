package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef
import com.example.schoolink.data.entities.relations.GroupWithProfessor


@Dao
interface GroupProfessorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroupProfessorCrossRef(crossRef: GroupProfessorCrossRef)

    @Transaction
    @Query("SELECT * FROM professors WHERE professorId = :professorId")
    suspend fun getGroupsWithProfessor(professorId: Int): GroupWithProfessor?

    @Transaction
    @Query("DELETE FROM group_professor WHERE groupId = :groupId AND professorId = :professorId")
    suspend fun removeGroupFromProfessor(groupId: Int, professorId: Int)
}