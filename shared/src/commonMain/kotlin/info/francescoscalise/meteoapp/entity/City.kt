package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    @SerialName("city")
    var name: String,
    var state: String? = null,
    var country: String? = null,
    var location: Location? = null,
    var current: Current? = null
)