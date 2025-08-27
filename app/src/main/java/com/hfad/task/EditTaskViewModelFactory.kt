package com.hfad.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditTaskViewModelFactory(val taskId:Long, val dao:TaskDao):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditTaskViewModel::class.java)){
            return EditTaskViewModel(taskId,dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}