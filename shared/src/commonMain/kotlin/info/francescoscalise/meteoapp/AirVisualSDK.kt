package info.francescoscalise.meteoapp

import info.francescoscalise.meteoapp.entity.City
import info.francescoscalise.meteoapp.entity.Country
import info.francescoscalise.meteoapp.entity.State
import info.francescoscalise.meteoapp.network.ServiceAPI
import io.ktor.client.*

class AirVisualSDK {
    private val serviceAPI = ServiceAPI()

    @Throws(Exception::class) suspend fun getAllCountries(): List<Country>{
        return serviceAPI.getAllCountries().data!!
    }

    @Throws(Exception::class) suspend fun getStateByCountry(country: Country): List<State>{
        return serviceAPI.getStates(country.name).data!!
    }

    @Throws(Exception::class) suspend fun getCities(country: Country, state: State): List<City>{
        return serviceAPI.getCities(country.name, state.name).data!!
    }

    @Throws(Exception::class) suspend fun getCity(country: Country, state: State, city: City): City{
        return serviceAPI.getCity(country.name, state.name, city.name).data!!
    }

    @Throws(Exception::class) suspend fun getNearestCity(latitude: Double, longitude: Double): City{
        return serviceAPI.getNearestCity(latitude, longitude).data!!
    }
}