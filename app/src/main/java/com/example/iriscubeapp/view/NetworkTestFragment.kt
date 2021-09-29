package com.example.iriscubeapp.view

import MovementException
import SampleData
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.recycleBankMovement.HeaderAdapter
import com.example.exampleapp.recycleBankMovement.RecycleMovementAdapter
import com.example.iriscubeapp.R
import com.example.iriscubeapp.contract.NetworkTestContract
import com.example.iriscubeapp.presenter.NetworkTestPresenter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_network_test.*
import kotlinx.coroutines.*
import retrofit2.Response
import kotlin.random.Random

class NetworkTestFragment : Fragment(), NetworkTestContract.View {
    /**
     * Static constants and functions
     * */
    companion object {
        const val TAG = "NetworkTestFragment"
        fun newInstance() = NetworkTestFragment()
    }

    private val newMovementActivityRequestCode = 1
    private val presenter = NetworkTestPresenter()
    private val movementAdapter =
        RecycleMovementAdapter { sampleData -> adapterOnClick(sampleData) }
    private val headerAdapter = HeaderAdapter()


    /**
     * [Fragment] functions
     * */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.bindView(view = this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_network_test, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycle_view)
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = movementAdapter
        recyclerView.apply {
            //edgeEffectFactory = BounceEdgeEffectFactory()
        }



        return view
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG, color: Int) {
        val snackbar: Snackbar = Snackbar.make(this, message, duration)
        snackbar.setBackgroundTint(color)
        snackbar.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = runBlocking {
        super.onViewCreated(view, savedInstanceState)
        onGetMovementsClick()
    }

    fun onGetMovementsClick() {

        nextTodoButton.setOnClickListener {
            presenter.getMovement()
            headerAdapter.updateMovementCount(10)


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts movement into viewModel. */
        if (requestCode == newMovementActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let {
                val movementName = "t"
                val movementDescription = "d"
                val movementValue = 1.0

                presenter.insertMovement(movementName, movementDescription, movementValue)
            }
        }
    }

    private suspend fun getMethod(result: Response<Array<SampleData>>): List<SampleData> =
        coroutineScope {
            val list = ArrayList<SampleData>()
            if (result.isSuccessful) {
                val gson = GsonBuilder().setPrettyPrinting().create()
                var i = 0
                while (i < result.body()?.size ?: 0) {
                    result.body()?.elementAt(i)?.let { list.add(it) }
                    ++i
                }

            } else {
                Log.e("RETROFIT_ERROR", result.code().toString())
            }
            println("Lista riempita : $list")
            return@coroutineScope list
        }


    @SuppressLint("SetTextI18n")
    private fun adapterOnClick(movement: SampleData) {
        val dialog = context?.let { it1 -> BottomSheetDialog(it1) }

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.fragment_second, null)

        // on below line we are creating a variable for our button
        // which we are using to dismiss our dialog.
        val stb = AnimationUtils.loadAnimation(context, R.anim.start_anim_cardview2)
        val btnClose = view.findViewById<Button>(R.id.idBtnDismiss)

        btnClose.startAnimation(stb)

        val titleText: TextView = view.findViewById(R.id.movement_title2)
        val valueText: TextView = view.findViewById(R.id.movement_value2)
        val descriptionText: TextView = view.findViewById(R.id.movement_description2)

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


    /**
     * [NetworkTestContract.View] implementation
     * */
    override fun onMovementsAvailable(result: Response<Array<SampleData>>): Unit = runBlocking {
        val list = getMethod(result)
        println("RESULT : $list")
        movementAdapter.submitList(list)
        view?.snack("Lista creata correttamente", color = Color.rgb(80, 170, 80))
    }

    override fun onMovementsError(error: MovementException) {
        view?.snack("Impossibile creare la lista", color = Color.rgb(180, 40, 80))
    }

    override fun onMovementsError(error: Exception) {
        view?.snack("Impossibile creare la lista", color = Color.rgb(180, 40, 80))
    }
}