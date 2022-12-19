package com.hfad.letsdoit.fragments.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hfad.letsdoit.R
import com.hfad.letsdoit.data.models.Priority
import com.hfad.letsdoit.data.models.ToDoData
import com.hfad.letsdoit.databinding.FragmentListBinding
import com.hfad.letsdoit.databinding.RowLayoutBinding

class ListAdapter(private var list: List<ToDoData>, private val context: Context) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var dataList = emptyList<ToDoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        return MyViewHolder(
            RowLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        holder.rowLayoutBinding.tvTitle.text = dataList[position].title
        holder.rowLayoutBinding.tvDescription.text = dataList[position].description
        when (dataList[position].priority) {
            Priority.HIGH -> holder.rowLayoutBinding.cvPriority.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.red
                )
            )
            Priority.MEDIUM -> holder.rowLayoutBinding.cvPriority.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.yellow
                )
            )
            Priority.LOW -> holder.rowLayoutBinding.cvPriority.setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.green
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(toDoData: List<ToDoData>){
        this.dataList = toDoData
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val rowLayoutBinding: RowLayoutBinding) :
        RecyclerView.ViewHolder(rowLayoutBinding.root) {
    }



}