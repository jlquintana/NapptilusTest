package com.joseluisquintana.napptilustest.app.list.filter

import android.app.DialogFragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joseluisquintana.domain.Filter
import com.joseluisquintana.napptilustest.app.R
import kotlinx.android.synthetic.main.fragment_filter_dialog.view.*

class FilterDialogFragment : DialogFragment() {

    private var mListener: OnFilterListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter_dialog, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.cancelB?.setOnClickListener { dismiss() }
        view?.closeB?.setOnClickListener { onClosePressed() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFilterListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    private fun onClosePressed() {
        mListener?.filter(createFilter())
        mListener = null
        dismiss()
    }

    private fun createFilter(): Filter {
        return Filter(
            view?.maleCB?.isChecked ?: false,
            view?.femaleCB?.isChecked ?: false,
            view?.developerCB?.isChecked ?: false,
            view?.metalworkerCB?.isChecked ?: false,
            view?.brewerCB?.isChecked ?: false,
            view?.gemcutterCB?.isChecked ?: false,
            view?.medicCB?.isChecked ?: false
        )
    }

    interface OnFilterListener {
        fun filter(filter: Filter)
    }
}
