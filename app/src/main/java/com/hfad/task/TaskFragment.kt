package com.hfad.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.task.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private var _binding:FragmentTaskBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel: TaskViewModel
    lateinit var viewModelFactory: TaskViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater,container,false)
        val view = binding.root
        val application = requireActivity().application
        val dao = TaskDatabase.getInstance(application).taskDao()
        viewModelFactory = TaskViewModelFactory(dao)
        viewModel = ViewModelProvider(this,viewModelFactory).get(TaskViewModel::class.java)
        binding.viewModel = viewModel
        val adapter = TaskItemAdapter{viewModel.onTaskClicked(it)}
        binding.tasksList.adapter=adapter
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.navigateToTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                val action = TaskFragmentDirections.actionTaskFragmentToEditTaskFragment(it)
                view.findNavController().navigate(action)
                viewModel.onTaskNavigate()
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}