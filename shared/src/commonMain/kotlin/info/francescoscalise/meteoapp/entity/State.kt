package info.francescoscalise.meteoapp.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class State(
    @SerialName("state")
    val name: String
)