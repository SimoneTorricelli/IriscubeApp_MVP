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
    inner class MovementViewHolder(itemView: View, val onClick: (SampleData) -> Unit) :
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

        }

        /*
        funzione per fare il bind delle TodoClass o qualsiasi altra classe

        fun bindTodo(toDoObject: TodoClass) {
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
        */

    }

    /*

    Nuovo ModelView con la scelta del tipo

    //--------onCreateViewHolder: inflate layout with view holder-------override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
    return when (viewType) {
        TYPE_FAMILY -> {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.family_item, parent, false)
            FamilyViewHolder(view)
        }
        TYPE_FRIEND -> {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.friend_item, parent, false)
            FriendViewHolder(view)
        }
        TYPE_COLLEAGUE -> {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.colleague_item, parent, false)
            ColleagueViewHolder(view)
        }
        else -> throw IllegalArgumentException("Invalid view type")
    }
}
//-----------onCreateViewHolder: bind view with data model---------
override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
    val element = adapterDataList[position]
    when (holder) {
        is FamilyViewHolder -> holder.bind(element as FamilyDataModel)
        is FriendViewHolder -> holder.bind(element as FriendDataModel)
        is ColleagueViewHolder -> holder.bind(element as ColleagueDataModel)
        else -> throw IllegalArgumentException()
    }
}
override fun getItemViewType(position: Int): Int {
    val comparable = data[position]
    return when (comparable) {
        is String -> TYPE_FAMILY
        is Trailer -> TYPE_FRIEND
        is Review -> TYPE_COLLEAGUE
        else -> throw IllegalArgumentException("Invalid type of data " + position)
    }
}
     */


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

