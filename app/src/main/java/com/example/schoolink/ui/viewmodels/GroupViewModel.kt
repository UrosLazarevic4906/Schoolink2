package com.example.schoolink.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.models.ProfessorModel
import com.example.schoolink.domain.repository.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupViewModel(
    private val repository: GroupRepository
) : ViewModel() {

    var currentGroup: GroupModel? = null

    fun createGroup(group: GroupModel, onGroupCreated: (Long) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val groupId = repository.createGroup(group)
                withContext(Dispatchers.Main) {
                    if (groupId > 0) {
                        onGroupCreated(groupId)
                    } else {
                        throw Exception("Group creation failed, invalid ID: $groupId")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("GroupViewModel", "Error creating group", e)
                    throw e
                }
            }
        }
    }

    fun deleteGroup(group: GroupModel, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGroup(group)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }

    fun updateGroupAsync(group: GroupModel, onComplete: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGroup(group)
            withContext(Dispatchers.Main) {
                onComplete()
            }
        }
    }


    fun getGroupById(groupId: Int, onResult: (GroupModel?) -> Unit){
        viewModelScope.launch {
            val group = withContext(Dispatchers.IO) {
                repository.getGroupById(groupId)
            }
            currentGroup = group
            onResult(group)
        }
    }

}