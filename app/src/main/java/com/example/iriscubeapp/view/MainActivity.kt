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

        val mDrawer : FlowingDrawer = findViewById(R.id.drawerlayout)
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL)
        mDrawer.setOnDrawerStateChangeListener(object : ElasticDrawer.OnDrawerStateChangeListener {
            override fun onDrawerStateChange(oldState: Int, newState: Int) {
                if (newState == ElasticDrawer.STATE_CLOSED) {
                    Log.i("MainActivity", "Drawer STATE_CLOSED")
                }
            }

            override fun onDrawerSlide(openRatio: Float, offsetPixels: Int) {
                Log.i("MainActivity", "openRatio=$openRatio ,offsetPixels=$offsetPixels")
            }
        })

        supportFragmentManager.beginTransaction()
            .replace(R.id.mainActivityFragmentContainer, NetworkTestFragment()).commit()

    }


}