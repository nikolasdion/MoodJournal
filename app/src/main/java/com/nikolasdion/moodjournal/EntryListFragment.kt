package com.nikolasdion.moodjournal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.nikolasdion.moodjournal.util.Logger
import kotlinx.android.synthetic.main.fragment_entry_list.*
import kotlinx.android.synthetic.main.fragment_entry_list.view.*

/**
 * A fragment representing a list of [Entry].
 */
class EntryListFragment : Fragment() {
    private val log = Logger(this::class.java.simpleName)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProviders.of(this).get(EntryListViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_entry_list, container, false)
        val adapter = EntryAdapter()

        // Set the adapter
        view.entryListRecyclerView.apply{
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        // Change the list in the adapter is the data changes.
        viewModel.allEntries.observe(this, Observer { entries ->
            run {
                log.i("Entries updated to: $entries")
                adapter.submitList(entries)
            }
        })

        view.fab.setOnClickListener {
            log.i("Clicked FAB to create new entry")
            it.findNavController().navigate(R.id.action_entryListFragment_to_editEntryFragment, null)
        }

        return view
    }
}
