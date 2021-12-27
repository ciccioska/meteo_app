package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    @SerialName("city")
    var name: String,
    @SerialName("state")
    var state: String,
    @SerialName("country")
    var country: String
)