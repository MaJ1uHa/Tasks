package com.hfad.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.hfad.task.databinding.FragmentEditTaskBinding


class EditTaskFragment : Fragment() {
    private var _binding:FragmentEditTaskBinding?=null
    private val binding get () = _binding!!
    lateinit var viewModel:EditTaskViewModel
    lateinit var viewModelFactory:EditTaskViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding=FragmentEditTaskBinding.inflate(inflater,container,false)
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        val dao = TaskDatabase.getInstance(requireActivity().application).taskDao()
        viewModelFactory = EditTaskViewModelFactory(taskId,dao)
        viewModel = ViewModelProvider(this,viewModelFactory).get(EditTaskViewModel::class.java)
        binding.viewModel=viewModel
        val view = binding.root
        viewModel.navigateToList.observe(viewLifecycleOwner, Observer {
            view.findNavController().navigate(R.id.action_editTaskFragment_to_taskFragment)
        })
        binding.lifecycleOwner=viewLifecycleOwner
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}