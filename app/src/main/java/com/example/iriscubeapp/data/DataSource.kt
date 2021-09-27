
import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

/* Handles operations on MovementLiveData and holds details about it. */
class DataSource(val resources: Resources, val context: Context) {

    private suspend fun takeList() = coroutineScope {
        val initialMovementList = movementDatas(context)
        val movementLiveData = MutableLiveData(initialMovementList)
        delay(1000L)
        println("Passato 1 secondo invio")
        return@coroutineScope movementLiveData
    }

    /*
    /* Adds movement to liveData and posts value. */
    fun addMovement(movement: SampleData) {
        val currentList = movementLiveData.value
        if (currentList == null) {
            movementLiveData.postValue(listOf(movement))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, movement)
            movementLiveData.postValue(updatedList)
        }
    }

    /* Removes movement from liveData and posts value. */
    fun removeMovement(movement: SampleData) {
        val currentList = movementLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(movement)
            movementLiveData.postValue(updatedList)
        }
    }

    /* Returns movement given an ID. */
    fun getMovementForId(id: Long): SampleData? {
        movementLiveData.value?.let { movements ->
             movements.firstOrNull{ id == id}
        }
        return null
    }*/

    fun getMovementList(): LiveData<List<SampleData>> = runBlocking(CoroutineName("MakeList") + Dispatchers.Default) {
        println("[${Thread.currentThread().name}]")
        return@runBlocking takeList()
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources,context: Context): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources,context)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}