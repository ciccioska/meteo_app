package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.City
import kotlinx.serialization.Serializable

@Serializable
class CityResponse {
    var status: String? = null
    var data: City? = null
}