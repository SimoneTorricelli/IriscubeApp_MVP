package com.example.iriscubeapp.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.iriscubeapp.R
import com.mxn.soul.flowingdrawer_core.ElasticDrawer
import com.mxn.soul.flowingdrawer_core.FlowingDrawer


class MainActivity : AppCompatActivity() {
    /**
     * [AppCompatActivity] functions
     * */

    //MVP Branch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFragmentContainer, NetworkTestFragment()).commit()

    }


}