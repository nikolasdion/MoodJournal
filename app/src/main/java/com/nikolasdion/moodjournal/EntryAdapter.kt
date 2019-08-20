package com.nikolasdion.moodjournal

import android.text.format.DateFormat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

import kotlinx.android.synthetic.main.fragment_entry.view.*
import android.os.Bundle

/**
 * [ListAdapter] that can display a list of [Entry].
 */
class EntryAdapter : ListAdapter<Entry, EntryAdapter.EntryViewHolder>(EntryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = getItem(position)
        holder.itemView.date.text = DateFormat.format("yyyy-MM-dd hh:mm:ss", entry.date)
        holder.itemView.trigger.text = entry.trigger
        holder.itemView.setOnClickListener(createOnClickListener(entry.id))
        println("Draw View for entry: $entry")
    }

    private fun createOnClickListener(entryId: Int): View.OnClickListener {
        println("Selected entry with ID: $entryId")

        return View.OnClickListener {
            val navController = it.findNavController()

            val args = Bundle().apply {
                putInt(ARG_ENTRY_ID, entryId)
            }

            navController.navigate(R.id.action_entryListFragment_to_editEntryFragment, args)
        }
    }

    inner class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        override fun toString(): String {
            return super.toString() + " '" + itemView.date.text + "'"
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
