package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.City
import info.francescoscalise.meteoapp.entity.Country
import kotlinx.serialization.Serializable

@Serializable
class ListCitiesResponse {
    var status: String? = null
    var data: List<City>? = null
}