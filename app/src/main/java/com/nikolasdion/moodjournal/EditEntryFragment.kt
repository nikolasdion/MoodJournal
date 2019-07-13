package com.nikolasdion.moodjournal

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_edit_entry.*

private const val ARG_ENTRY_ID = "entry_id"

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
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var viewModel: EntryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            entryId = it.getInt(ARG_ENTRY_ID)
        }
        viewModel = ViewModelProviders.of(this).get(EntryListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_entry, container, false)
    }

    fun onSaveButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)

        val newEntry = Entry(entryId?: 0,
            System.currentTimeMillis(),
            trigger_text_field.editText.toString(),
            thoughts_text_field.editText.toString(),
            feelings_text_field.editText.toString(),
            physical_text_field.editText.toString(),
            behaviour_text_field.editText.toString(),
            notes_text_field.editText.toString())

        viewModel.insert(newEntry)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
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
