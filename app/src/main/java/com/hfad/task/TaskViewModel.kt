package com.hfad.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(val dao: TaskDao): ViewModel() {
    var newTaskName = ""
    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask:LiveData<Long?> get()=_navigateToTask
    val tasks = dao.getAll()
    fun addTask(){
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }
    fun onTaskClicked(taskId:Long){
        _navigateToTask.value=taskId
    }
    fun onTaskNavigate(){
        _navigateToTask.value=null
    }
}