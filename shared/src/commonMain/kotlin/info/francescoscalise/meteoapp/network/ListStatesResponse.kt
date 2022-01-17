package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.NationState
import kotlinx.serialization.Serializable

@Serializable
class ListStatesResponse {
    var status: String? = null
    var data: List<NationState>? = null
}