package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Pollution (
    @SerialName("ts")
    var date: String? = null,
    @SerialName("aqius")
    var pollutionIndicatorUS: Double? = null,
    @SerialName("mainus")
    var polluteMainUS: String? = null,
    @SerialName("aqicn")
    var pollutionIndicatorCN: Double? = null,
    @SerialName("maincn")
    var polluteMainCN: String? = null,
)
