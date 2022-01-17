package info.francescoscalise.meteoapp

import info.francescoscalise.meteoapp.entity.City
import info.francescoscalise.meteoapp.entity.Country
import info.francescoscalise.meteoapp.entity.NationState
import info.francescoscalise.meteoapp.network.ServiceAPI

class AirVisualSDK {
    private val serviceAPI = ServiceAPI()

    @Throws(Exception::class) suspend fun getAllCountries(): List<Country>{
        return serviceAPI.getAllCountries().data!!
    }

    @Throws(Exception::class) suspend fun getStateByCountry(country: Country): List<NationState>{
        return serviceAPI.getStates(country.name).data!!
    }

    @Throws(Exception::class) suspend fun getCities(country: Country, nationState: NationState): List<City>{
        return serviceAPI.getCities(country.name, nationState.name).data!!
    }

    @Throws(Exception::class) suspend fun getCity(country: Country, nationState: NationState, city: City): City{
        return serviceAPI.getCity(country.name, nationState.name, city.name).data!!
    }

    @Throws(Exception::class) suspend fun getNearestCity(latitude: Double, longitude: Double): City{
        return serviceAPI.getNearestCity(latitude, longitude).data!!
    }
}