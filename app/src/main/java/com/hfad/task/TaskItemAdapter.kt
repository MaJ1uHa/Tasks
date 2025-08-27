package com.hfad.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.hfad.task.databinding.TaskItemBinding

class TaskItemAdapter(val clickListener: (taskId:Long) ->Unit):ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater,parent,false)
        return TaskItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.task=item
        holder.binding.root.setOnClickListener{
            clickListener(item.taskId)
        }
    }

    class TaskItemViewHolder(val binding:TaskItemBinding) : RecyclerView.ViewHolder(binding.root)
}