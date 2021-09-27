package com.example.iriscubeapp.view

import SampleData
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.recycleBankMovement.HeaderAdapter
import com.example.exampleapp.recycleBankMovement.MovementListViewModel
import com.example.exampleapp.recycleBankMovement.MovementsListViewModelFactory
import com.example.exampleapp.recycleBankMovement.RecycleMovementAdapter
import com.example.iriscubeapp.R

class SecondFragment : Fragment() {

    private val newMovementActivityRequestCode = 1
    private val movementsListViewModel by viewModels<MovementListViewModel> {
        MovementsListViewModelFactory(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_second, container, false)

        val headerAdapter = HeaderAdapter()
        val movementAdapter = RecycleMovementAdapter { sampleData -> adapterOnClick(sampleData) }
        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view)
        recyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movementAdapter


        movementsListViewModel.movementData.observe(viewLifecycleOwner, {
            it?.let {
                movementAdapter.submitList(it as MutableList<SampleData>)
                headerAdapter.updateMovementCount(it.size)
            }
        })

        return view
    }

    private fun adapterOnClick(movement: SampleData) {

        /*
        val intent = Intent(this, MovementDetailActivity()::class.java)
        intent.putExtra(MOVEMENT_ID, movement.id)
        startActivity(intent)
        */

    }

    /* Adds sampleMovementData to MovementList when FAB is clicked. */
    private fun fabOnClick() {
        println("Fab Pressed")
        //val intent = Intent(this, AddMovementActivity::class.java)
        //startActivityForResult(intent, newMovementActivityRequestCode)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts movement into viewModel. */
        if (requestCode == newMovementActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let {
                val movementName = "t"
                val movementDescription = "d"
                val movementValue = 1.0

                movementsListViewModel.insertMovement(movementName, movementDescription,movementValue)
            }
        }
    }


}