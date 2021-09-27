package com.example.iriscubeapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.iriscubeapp.R


class MainActivity : AppCompatActivity(){
    /**
     * [AppCompatActivity] functions
     * */

    //MVP Branch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.mainActivityFragmentContainer,FirstFragment()).commit()

    }




}