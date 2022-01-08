package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather (
    @SerialName("ts")
    var date: String? = null,
    @SerialName("tp")
    var temperature: Double? = null,
    @SerialName("pr")
    var pressure: Double? = null,
    @SerialName("hu")
    var humidity: Double? = null,
    @SerialName("ws")
    var windSpeed: Double? = null,
    @SerialName("wd")
    var windDirection: Double? = null,
    @SerialName("ic")
    var wheatherIcon: String? = null,
)
