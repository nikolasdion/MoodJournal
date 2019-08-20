package com.nikolasdion.moodjournal

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_edit_entry.*

const val ARG_ENTRY_ID = "entry_id"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [EditEntryFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [EditEntryFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class EditEntryFragment : Fragment() {
    private var entryId: Int? = null
    private lateinit var viewModel: EntryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        entryId = arguments?.getInt(ARG_ENTRY_ID)
        println("CCCCC" + entryId)
        viewModel = ViewModelProviders.of(this).get(EntryListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_entry, container, false)

        entrySaveButton.setOnClickListener { onSaveButtonPressed() }

        return view
    }

    override fun onResume() {
        super.onResume()

        entryId?.let {
            val entry = viewModel.getEntryFromId(it).value

            println("BBBB" + viewModel.getEntryFromId(it))

            println("BBBBBBBBBBBBB" + entry.toString())

            triggerTextField.editText?.setText(entry?.trigger)
            thoughtsTextField.editText?.setText(entry?.thoughts)
            feelingsTextField.editText?.setText(entry?.feelings)
            physicalTextField.editText?.setText(entry?.physical)
            behaviourTextField.editText?.setText(entry?.behaviour)
            notesTextField.editText?.setText(entry?.notes)
        }
    }

    private fun onSaveButtonPressed() {
        val entryIdToSave = entryId?: 0

        val newEntry = Entry(entryIdToSave,
            System.currentTimeMillis(),
            triggerTextField.editText?.text.toString(),
            thoughtsTextField.editText?.text.toString(),
            feelingsTextField.editText?.text.toString(),
            physicalTextField.editText?.text.toString(),
            behaviourTextField.editText?.text.toString(),
            notesTextField.editText?.text.toString())

        println("BBBBBB" + newEntry.toString())

        if (entryId != null) {
            viewModel.update(newEntry)
        }
        else
        {
            viewModel.insert(newEntry)
        }

        val inputMethodManager = activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)

        fragmentManager?.popBackStack()
    }

    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment EditEntryFragment.
//         */
//        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(entryId: Int) =
                EditEntryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ENTRY_ID, entryId)
                }
            }
    }
}
