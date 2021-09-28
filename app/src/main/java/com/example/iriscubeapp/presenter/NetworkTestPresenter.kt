package com.example.iriscubeapp.presenter

import android.content.Context
import com.example.iriscubeapp.contract.NetworkTestContract
import com.example.iriscubeapp.model.networking.Repository
import com.example.iriscubeapp.model.networking.MovementException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NetworkTestPresenter : CoroutineScope, NetworkTestContract.Presenter {
    /**
     * [CoroutineScope] implementation
     * */
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main


    private var view: NetworkTestContract.View? = null


    /**
     * [NetworkTestContract.Presenter] implementation
     * */
    override fun bindView(view: NetworkTestContract.View) {
        this.view = view
    }


    override fun getMovement(context: Context) {
        launch {
            try {
                val response = Repository.getMovement(context)
                println("response in NetworkTestPresenter : $response")
                view?.onMovementsAvailable(response)
            } catch (error: MovementException) {
                view?.onMovementsError(error)
            } catch (genericError: Exception) {
                view?.onMovementsError(genericError)
            }
        }
    }
}