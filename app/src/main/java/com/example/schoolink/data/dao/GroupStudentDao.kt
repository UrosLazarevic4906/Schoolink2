package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.schoolink.data.entities.refference.GroupStudentCrossRef
import com.example.schoolink.data.entities.relations.GroupWithStudent

@Dao
interface GroupStudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroupStudentCrossRef(crossRef: GroupStudentCrossRef)

    @Transaction
    @Query("SELECT * FROM groups WHERE groupId = :groupId")
    suspend fun getGroupWithStudents(groupId: Int): GroupWithStudent?

    @Transaction
    @Query("DELETE FROM group_student WHERE groupId = :groupId AND studentId = :studentId")
    suspend fun removeStudentFromGroup(groupId: Int, studentId: Int)

    @Transaction
    @Query("DELETE FROM group_student WHERE groupId = :groupId")
    suspend fun removeAllStudentsFromGroup(groupId: Int)
}
