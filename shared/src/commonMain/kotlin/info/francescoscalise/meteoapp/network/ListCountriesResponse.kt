package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.Country
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
class ListCountriesResponse {
    var status: String? = null
    var data: List<Country>? = null
}