import android.content.Context
import android.content.res.Resources
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import kotlinx.coroutines.coroutineScope
import kotlin.collections.ArrayList


suspend fun movementDatas(context: Context): List<SampleData> = coroutineScope {
    val url = "https://mocki.io/v1/4da288b1-5179-4202-8cde-9fb6895f7c69"
    val list = ArrayList<SampleData>()
    val que = Volley.newRequestQueue(context)
    val jsonObjectRequest = JsonArrayRequest(
        Request.Method.GET, url, null,
        { response ->
            println(response)
            val gson = GsonBuilder().create()
            //val Model= gson.fromJson(response.getString(0),Array<sampleData>::class.java).toList()
            var i = 0
            while (i < response.length()) {
                val bankAccount = gson.fromJson(
                    response.getString(i),
                    SampleData(description = "", title = "", value = 0.0, id = 0).javaClass
                )
                println(bankAccount.toString())
                list.add(bankAccount)
                ++i
            }
        },
        { error ->
            println(error)
        }
    )
    que.add(jsonObjectRequest)
    return@coroutineScope list
}