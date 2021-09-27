package com.example.iriscubeapp.contract

import SampleData
import com.example.iriscubeapp.presenter.NetworkTestPresenter
import com.example.iriscubeapp.view.NetworkTestFragment

interface NetworkTestContract {
    /**
     * Interface implemented by [NetworkTestFragment]
     * */
    interface View {
        fun onMovementsAvailable(result: Result<SampleData>)
        fun onMovementsError()
    }

    /**
     * Interface implemented by [NetworkTestPresenter]
     * */
    interface Presenter {
        fun bindView(view: View)
        fun getMovement()
    }
}