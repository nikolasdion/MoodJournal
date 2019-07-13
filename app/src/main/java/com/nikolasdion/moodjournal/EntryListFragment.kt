package com.nikolasdion.moodjournal

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of [Entry].
 */
class EntryListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProviders.of(this).get(EntryListViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_entry_list, container, false)
        val adapter = EntryAdapter()

        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        // Set the adapter
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = LinearLayoutManager(context)
                this.adapter = adapter
            }
        }

        // Change the list in the adapter is the data changes.
        viewModel.allEntries.observe(this, Observer { entries ->
            run {
                adapter.submitList(entries)
            }
        })

        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_entryListFragment_to_editEntryFragment, null)
        }

        return view
    }
}
