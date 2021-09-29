package com.example.exampleapp.recycleBankMovement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iriscubeapp.R
import SampleData


class RecycleMovementAdapter(private val onClick: (SampleData) -> Unit) :
    ListAdapter<SampleData, RecycleMovementAdapter.MovementViewHolder>(MovementDiffCallback) {

    /* ViewHolder for sampleMovementData, takes in the inflated view and the onClick behavior. */
    class MovementViewHolder(itemView: View, val onClick: (SampleData) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val movementTitle: TextView = itemView.findViewById(R.id.movement_title)
        private val movementImageView: ImageView = itemView.findViewById(R.id.movement_image)
        private val movementDescription: TextView = itemView.findViewById(R.id.movement_description)
        private val movementValue: TextView = itemView.findViewById(R.id.movement_value)
        private var currentMovement: SampleData? = null

        init {
            itemView.setOnClickListener {
                currentMovement?.let {
                    println("cliccato $it")
                    onClick(it)
                }
            }
        }


        fun bind(movement: SampleData) {
            currentMovement = movement
            movementTitle.text = movement.title
            movementDescription.text = movement.description
            movementValue.text = movement.value.toString() + " €"
            movementImageView.setImageResource(R.drawable.ic_action_name)
            //if (movement.image != null) {
            // movementImageView.setImageResource(movement.image)
            //} else {
            //   movementImageView.setImageResource(R.drawable.abc_vector_test)
            //}
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movement_data, parent, false)
        return MovementViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        val movement = getItem(position)
        holder.bind(movement)

    }
}

object MovementDiffCallback : DiffUtil.ItemCallback<SampleData>() {
    override fun areItemsTheSame(oldItem: SampleData, newItem: SampleData): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: SampleData, newItem: SampleData): Boolean {
        return oldItem.id == newItem.id
    }
}