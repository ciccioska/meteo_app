package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.State
import kotlinx.serialization.Serializable

@Serializable
class ListStatesResponse {
    var status: String? = null
    var data: List<State>? = null
}