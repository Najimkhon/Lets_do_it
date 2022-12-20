package com.hfad.letsdoit.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hfad.letsdoit.R
import com.hfad.letsdoit.data.models.ToDoData
import com.hfad.letsdoit.data.viewmodel.ToDoViewModel
import com.hfad.letsdoit.databinding.FragmentUpdateBinding
import com.hfad.letsdoit.fragments.SharedViewModel

class UpdateFragment : Fragment() {

    val args by navArgs<UpdateFragmentArgs>()
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mToDoViewModel: ToDoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        //binding.args = args
        val view = binding.root


        binding.etCurrentTitle.setText(args.currentItem.title)
        binding.etCurrentDescription.setText(args.currentItem.description)
        binding.spCurrentPriorities.setSelection(mSharedViewModel.parsePriorityToInt(args.currentItem.priority))
        binding.spCurrentPriorities.onItemSelectedListener = mSharedViewModel.listener
        return view

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveUpdates) {
            updateItem()
        }else if(item.itemId == R.id.delete){
            deleteItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateItem() {
        var spinnerValue = mSharedViewModel.parsePriority(binding.spCurrentPriorities.selectedItem.toString())
        val validation = mSharedViewModel.verifyDataFromUser(
            binding.etCurrentTitle.text.toString(),
            binding.etCurrentDescription.text.toString(),
            )
        if (validation){
            val updatedItem = ToDoData(
                args.currentItem.id,
                binding.etCurrentTitle.text.toString(),
                spinnerValue,
                binding.etCurrentDescription.text.toString()
            )
            mToDoViewModel.updateItem(updatedItem)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Item is updated!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Fill all the fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteItem(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mToDoViewModel.deleteItem(args.currentItem)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Successfully Removed", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete '${args.currentItem.title}'?")
        builder.setMessage("Are you sure you want to delete '${args.currentItem.title}'?")
        builder.create().show()

    }


}