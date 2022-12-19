package com.hfad.letsdoit.fragments.update

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.hfad.letsdoit.R
import com.hfad.letsdoit.data.models.Priority
import com.hfad.letsdoit.databinding.FragmentUpdateBinding
import com.hfad.letsdoit.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.etCurrentTitle.setText(args.currentItem.title)
        binding.etCurrentDescription.setText(args.currentItem.description)
        binding.spCurrentPriorities.setSelection(parsePriority(args.currentItem.priority))
        binding.spCurrentPriorities.onItemSelectedListener = mSharedViewModel.listener
        return view

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    private fun parsePriority(priority:Priority):Int{
        return when(priority){
            Priority.HIGH -> 0
            Priority.MEDIUM -> 1
            Priority.LOW -> 2
        }

    }

}