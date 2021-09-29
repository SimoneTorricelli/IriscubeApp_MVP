package com.example.iriscubeapp.presenter

import MovementException
import SampleData
import com.example.iriscubeapp.contract.NetworkTestContract
import com.example.iriscubeapp.model.networking.Repository
import com.example.iriscubeapp.model.networking.RetrofitClient
import com.example.iriscubeapp.model.networking.WebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

class NetworkTestPresenter : CoroutineScope, NetworkTestContract.Presenter {
    /**
     * [CoroutineScope] implementation
     * */
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main


    private var view: NetworkTestContract.View? = null
    private var client: WebService ? = null

    init {
        client = RetrofitClient.retrofit

    }



    /**
     * [NetworkTestContract.Presenter] implementation
     * */
    override fun bindView(view: NetworkTestContract.View) {
        this.view = view
    }

    override fun insertMovement(movementTitle: String?, movementDescription: String?,movementValue: Double?) {
        if (movementTitle == null || movementDescription == null || movementValue == null) {
            return
        }

        val newMovement = SampleData(
            Random.nextLong(),
            movementTitle,
            movementValue,
            //image,
            movementDescription,
        )

    }

    override fun getMovement() {
        launch {
            try {
                val response = client?.let { Repository(client = it).getMovement() }
                println("response in NetworkTestPresenter : $response")
                if (response != null) {
                    view?.onMovementsAvailable(response)
                }
            } catch (error: MovementException) {
                view?.onMovementsError(error)
            } catch (genericError: Exception) {
                view?.onMovementsError(genericError)
            }
        }
    }
}