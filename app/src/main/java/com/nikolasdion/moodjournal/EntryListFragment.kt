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
import com.nikolasdion.moodjournal.dummy.DummyContent

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

        // Populate with dummy content
        for(item in DummyContent.ITEMS)
        {
            viewModel.insert(item)
        }

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
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

        return view
    }
}
