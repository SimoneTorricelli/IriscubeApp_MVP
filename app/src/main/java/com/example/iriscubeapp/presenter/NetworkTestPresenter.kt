package com.example.iriscubeapp.presenter

import com.example.iriscubeapp.contract.NetworkTestContract
import com.example.iriscubeapp.model.networking.Repository
import com.example.iriscubeapp.model.networking.TodoException
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

    override fun getMovement() {
        launch {
            try {
                val response = Repository.getMovement()
                view?.onMovementsAvailable(response)
            } catch (error: TodoException) {
                view?.onMovementsError()
            } catch (genericError: Exception) {
                view?.onMovementsError()
            }
        }
    }
}