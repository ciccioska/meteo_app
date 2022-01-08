package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    var type: String? = null,
    var coordinates: List<Float>? = null
)
