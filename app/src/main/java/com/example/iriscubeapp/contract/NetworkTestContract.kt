package com.example.iriscubeapp.contract

import MovementException
import SampleData
import com.example.iriscubeapp.presenter.NetworkTestPresenter
import com.example.iriscubeapp.view.NetworkTestFragment
import retrofit2.Response

interface NetworkTestContract {
    /**
     * Interface implemented by [NetworkTestFragment]
     * */
    interface View {
        fun onMovementsAvailable(result: Response<Array<SampleData>>)
        fun onMovementsError(error: MovementException)
        fun onMovementsError(error: Exception)
    }

    /**
     * Interface implemented by [NetworkTestPresenter]
     * */
    interface Presenter {
        fun bindView(view: View)
        fun getMovement()
        fun insertMovement(movementTitle: String?, movementDescription: String?,movementValue: Double?)
    }

    interface Repository{
        suspend fun getMovement() : Response<Array<SampleData>>
    }
}