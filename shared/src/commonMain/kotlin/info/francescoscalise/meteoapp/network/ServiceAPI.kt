package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.City
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

import io.ktor.client.features.json.serializer.KotlinxSerializer

class ServiceAPI {
    companion object {
        private const val AIR_VISUAL_ENDPOINT = "https://api.airvisual.com/v2/"
        private const val AIR_VISUAL_APIKEY = "05906369-b043-4664-9106-7eee43f367fe"
    }

    private val httpClient = HttpClient {
        print("HttpClient created")
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllCountries(): ListCountriesResponse {
        return httpClient.get(AIR_VISUAL_ENDPOINT + "countries"){
            parameter("key", AIR_VISUAL_APIKEY)
        }
    }

    suspend fun getStates(country: String): ListStatesResponse {
        return httpClient.get(AIR_VISUAL_ENDPOINT + "states"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
        }
    }

    suspend fun getCities(country: String, state: String): ListCitiesResponse {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "cities"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
            parameter("state", state)
        }
    }

    suspend fun getNearestCity(latitude: Double, longitude: Double): CityResponse {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "nearest_city"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("lat", latitude)
            parameter("lon", longitude)
        }
    }

    suspend fun getCity(country: String, state: String, city: String): CityResponse {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "city"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
            parameter("state", state)
            parameter("city", city)
        }
    }
}