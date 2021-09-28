package com.example.iriscubeapp.view

import SampleData
import com.example.iriscubeapp.presenter.NetworkTestPresenter
import junit.framework.TestCase
import retrofit2.Response

class NetworkTestFragmentTest : TestCase() {

    private val presenter = NetworkTestPresenter()

    fun testOnCreate() {
        presenter.getMovement()

    }

    fun testOnCreateView() {}

    fun testOnViewCreated() {}

    fun testOnMovementsAvailable() {}

    fun testOnMovementsError() {}

    fun testTestOnMovementsError() {}
}