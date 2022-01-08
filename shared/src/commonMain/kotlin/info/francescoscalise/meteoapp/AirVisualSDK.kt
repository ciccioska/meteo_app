package info.francescoscalise.meteoapp

import info.francescoscalise.meteoapp.entity.Country
import info.francescoscalise.meteoapp.network.ServiceAPI
import io.ktor.client.*

class AirVisualSDK {
    private val serviceAPI = ServiceAPI()

    @Throws(Exception::class) suspend fun getAllCountries(): List<Country>{
        return serviceAPI.getAllCountries().data!!
    }
}