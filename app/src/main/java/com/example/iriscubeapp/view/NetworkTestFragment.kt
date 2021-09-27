package com.example.iriscubeapp.view

import SampleData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.iriscubeapp.R
import com.example.iriscubeapp.contract.NetworkTestContract
import com.example.iriscubeapp.presenter.NetworkTestPresenter
import kotlinx.android.synthetic.main.fragment_network_test.*

class NetworkTestFragment : Fragment(), NetworkTestContract.View {
    /**
     * Static constants and functions
     * */
    companion object {
        const val TAG = "NetworkTestFragment"
        fun newInstance() = NetworkTestFragment()
    }


    private val presenter = NetworkTestPresenter()
    private var todoId = 0


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
        return inflater.inflate(R.layout.fragment_network_test, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextTodoButton.setOnClickListener {
            presenter.getMovement()
        }
    }

    /**
     * [NetworkTestContract.View] implementation
     * */
    override fun onMovementsAvailable(result: Result<SampleData>) {
        val list = ArrayList<SampleData>()
    }

    override fun onMovementsError() {
        Toast.makeText(
            context,
            "Oops, impossible to get the movements.",
            Toast.LENGTH_LONG
        ).show()
    }
}