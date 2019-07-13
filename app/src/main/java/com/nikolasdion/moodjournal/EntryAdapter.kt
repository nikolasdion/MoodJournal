package com.nikolasdion.moodjournal

import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import kotlinx.android.synthetic.main.fragment_entry.view.*

/**
 * [ListAdapter] that can display a list of [Entry].
 */
class EntryAdapter : ListAdapter<Entry, EntryAdapter.ViewHolder>(EntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_entry, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = getItem(position)
        holder.dateView.text = DateFormat.format("yyyy-MM-dd hh:mm:ss", entry.date)
        holder.triggerView.text = entry.trigger
        holder.itemView.setOnClickListener(createOnClickListener(entry.id))
    }

    private fun createOnClickListener(id: Int): View.OnClickListener {
        return View.OnClickListener {
            val navController = it.findNavController()
            navController.navigate(R.id.action_entryListFragment_to_editEntryFragment)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dateView: TextView = view.date
        val triggerView: TextView = view.trigger

        override fun toString(): String {
            return super.toString() + " '" + dateView.text + "'"
        }
    }

    private class EntryDiffCallback : DiffUtil.ItemCallback<Entry>() {

        override fun areItemsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry): Boolean {
            return oldItem == newItem
        }
    }
}
