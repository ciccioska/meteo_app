package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class Current (
    var weather: Weather? = null,
    var pollution: Pollution? = null,
)
