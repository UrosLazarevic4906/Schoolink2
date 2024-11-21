package com.example.schoolink.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolink.domain.models.GroupModel
import com.example.schoolink.domain.repository.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GroupViewModel(
    private val repository: GroupRepository
) : ViewModel() {

    var currentGroup: GroupModel? = null

    fun createGroup(group: GroupModel){
        viewModelScope.launch(Dispatchers.IO){
            repository.createGroup(group)
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