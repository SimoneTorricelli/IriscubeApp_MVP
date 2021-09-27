
import androidx.annotation.DrawableRes
import com.google.gson.annotations.SerializedName

data class SampleData (
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("value")
    val value: Double,
    //@DrawableRes
    //val image: Int?,
    @SerializedName("description")
    val description: String
)

data class MovementException(override val message: String, override val cause: Throwable) : Exception()