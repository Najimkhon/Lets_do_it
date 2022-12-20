package com.hfad.letsdoit.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.letsdoit.R
import com.hfad.letsdoit.data.viewmodel.ToDoViewModel
import com.hfad.letsdoit.databinding.FragmentListBinding
import com.hfad.letsdoit.fragments.SharedViewModel
import com.hfad.letsdoit.fragments.list.adapter.ListAdapter

class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val mToDoViewModel: ToDoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()
    private val adapter: ListAdapter by lazy { ListAdapter() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root

        mSharedViewModel.emptyDatabase.observe(viewLifecycleOwner, Observer {
            showEmptyDatabaseView(it)
        })

        setupRecyclerView()

        mToDoViewModel.getAllData.observe(viewLifecycleOwner, Observer {
            mSharedViewModel.checkIfDatabaseEmpty(it)
            adapter.setData(it)
        })

        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)

        return view
    }

    private fun setupRecyclerView(){
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        swipeToDelete(recyclerView)
    }

    private fun swipeToDelete(recyclerView: RecyclerView){
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val itemToDelete = adapter.dataList[viewHolder.adapterPosition]
                mToDoViewModel.deleteItem(itemToDelete)
                Toast.makeText(requireContext(), "Successfully deleted: '${itemToDelete.title}'", Toast.LENGTH_SHORT).show()
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun showEmptyDatabaseView(emptyDabase: Boolean) {
        if (emptyDabase){
            binding.ivNoData.visibility = View.VISIBLE
            binding.tvNoData.visibility = View.VISIBLE
        }else{
            binding.ivNoData.visibility = View.GONE
            binding.tvNoData.visibility = View.GONE
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll) {
            deleteAll()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun deleteAll(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mToDoViewModel.deleteAll()
            Toast.makeText(requireContext(), "All Records are Removed", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No"){_,_->}
        builder.setTitle("Delete All Records?")
        builder.setMessage("Are you sure you want to delete all records?")
        builder.create().show()
    }

}