package com.example.iriscubeapp.view

import SampleData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.iriscubeapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(movement: SampleData) : BottomSheetDialogFragment() {

    private val movement = movement

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_second, null)

        val titleText: TextView = view.findViewById(R.id.movement_title2)
        val valueText: TextView = view.findViewById(R.id.movement_value2)
        val descriptionText: TextView = view.findViewById(R.id.movement_description2)

        titleText.text = getString(R.string.titolo_movimento, movement.title)
        valueText.text = getString(R.string.quantita_movimento, movement.value)
        descriptionText.text = getString(R.string.descrizione_movimento, movement.description)

        return view
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}