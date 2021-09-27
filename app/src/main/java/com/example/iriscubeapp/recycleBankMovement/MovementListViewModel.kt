package com.example.exampleapp.recycleBankMovement
import DataSource
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import SampleData
import kotlin.random.Random
class MovementListViewModel(val dataSource: DataSource) : ViewModel() {

    val movementData = dataSource.getMovementList()

    /* If the name and description are present, create new Movement and add it to the datasource */
    fun insertMovement(movementTitle: String?, movementDescription: String?,movementValue: Double?) {
        if (movementTitle == null || movementDescription == null || movementValue == null) {
            return
        }

        //val image : Int? = null
        val newMovement = SampleData(
            Random.nextLong(),
            movementTitle,
            movementValue,
            //image,
            movementDescription,
        )

        //dataSource.addMovement(newMovement)
    }
}

class MovementsListViewModelFactory(private val context: Context?) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovementListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovementListViewModel(
                dataSource = DataSource.getDataSource(context!!.resources,context)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}