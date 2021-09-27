package com.example.iriscubeapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.recycleBankMovement.HeaderAdapter
import com.example.exampleapp.recycleBankMovement.RecycleMovementAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exampleapp.recycleBankMovement.MovementListViewModel
import com.example.exampleapp.recycleBankMovement.MovementsListViewModelFactory
import com.example.iriscubeapp.R
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.res.AssetManager
import android.graphics.Color
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.observe
import androidx.transition.TransitionManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.graphics.BitmapFactory

import SampleData
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import java.io.InputStream
import java.lang.Exception


const val FLOWER_ID = "movement id"

class FirstFragment : Fragment(){

    private val newMovementActivityRequestCode = 1
    private val movementsListViewModel by viewModels<MovementListViewModel> {
        MovementsListViewModelFactory(context)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        val ttb = AnimationUtils.loadAnimation(context,R.anim.start_anim)
        val stb = AnimationUtils.loadAnimation(context,R.anim.start_anim_cardview2)
        val cardView : CardView = view.findViewById(R.id.cardView)
        val cardViewMoney : CardView = view.findViewById(R.id.cardViewMoney)
        val diamondImage : ImageView = view.findViewById(R.id.diamondImage)

        val assetManager : AssetManager? = context?.assets
        try {
            assetManager?.let {
                val diamondAsset: InputStream = it.open("diamond.png")
                val bitmap = BitmapFactory.decodeStream(diamondAsset)
                diamondImage.setImageBitmap(bitmap)
            }
        }catch (e : Exception) {
            Log.e(TAG, e.toString())

        }

        cardView.startAnimation(ttb)
        cardViewMoney.startAnimation(stb)

        val headerAdapter = HeaderAdapter()
        val movementAdapter = RecycleMovementAdapter { sampleData -> adapterOnClick(sampleData)}
        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movementAdapter
        recyclerView.apply {
            //edgeEffectFactory = BounceEdgeEffectFactory()
        }


        movementsListViewModel.movementData.observe(viewLifecycleOwner, {
            it.let {
                movementAdapter.submitList(it as MutableList<SampleData>)
                headerAdapter.updateMovementCount(it.size)
            }
        })

        addConstraintSetAnimation(view)


        /*val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }*/

        return view
    }

    fun getHelloWorldString(): String {
        return "HELLO_WORLD"
    }

    private fun addConstraintSetAnimation(view: View) {

        var isCoverView = false

        val initialConstraint = ConstraintSet()
        val coverConstraint = ConstraintSet()
        coverConstraint.clone(context, R.layout.card_view)
        val constraintLayout : ConstraintLayout = view.findViewById(R.id.fragment_first)
        initialConstraint.clone(constraintLayout)

        val cardView : CardView = view.findViewById(R.id.cardView)
        val textView : TextView = view.findViewById(R.id.textConto)


        cardView.setOnClickListener {
            println("OK")
            if (!isCoverView) {
                TransitionManager.beginDelayedTransition(constraintLayout)
                coverConstraint.applyTo(constraintLayout)

                val anim = ValueAnimator()
                anim.setIntValues(Color.WHITE, Color.BLACK)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    textView.setTextColor(it.animatedValue as Int)
                }
                anim.duration = 300
                anim.start()
                isCoverView = true
            }
            else{
                TransitionManager.beginDelayedTransition(constraintLayout)
                initialConstraint.applyTo(constraintLayout)

                val anim = ValueAnimator()
                anim.setIntValues(Color.BLACK, Color.WHITE)
                anim.setEvaluator(ArgbEvaluator())
                anim.addUpdateListener {
                    textView.setTextColor(it.animatedValue as Int)
                }
                anim.duration = 300
                anim.start()
                isCoverView = false
            }

        }


    }


    @SuppressLint("SetTextI18n")
    private fun adapterOnClick(movement: SampleData) {
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.fragment_second, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val stb = AnimationUtils.loadAnimation(context,R.anim.start_anim_cardview2)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)

        btnClose.startAnimation(stb)

        val titleText : TextView= view.findViewById(R.id.movement_title2)
        val valueText : TextView= view.findViewById(R.id.movement_value2)
        val descriptionText : TextView= view.findViewById(R.id.movement_description2)

        titleText.text = "Titolo movimento: " + movement.title
        valueText.text = "Quantità movimento: " + movement.value.toString() + " €"
        descriptionText.text = "Descrizione movimento: " + movement.description




        // on below line we are adding on click listener
        // for our dismissing the dialog button.
        btnClose.setOnClickListener {
            // on below line we are calling a dismiss
            // method to close our dialog.
            dialog?.dismiss()
        }
        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog?.setCancelable(false)

        // on below line we are setting
        // content view to our view.
        dialog?.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog?.show()
        /*
        val intent = Intent(this, MovementDetailActivity()::class.java)
        intent.putExtra(MOVEMENT_ID, movement.id)
        startActivity(intent)
        */

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