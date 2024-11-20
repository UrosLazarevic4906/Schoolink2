package com.example.schoolink.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.schoolink.data.entities.refference.GroupProfessorCrossRef

@Dao
interface GroupProfessorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGroupProfessorCrossRef(crossRef: GroupProfessorCrossRef)
}
