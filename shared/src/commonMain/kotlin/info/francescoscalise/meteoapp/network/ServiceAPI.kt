package info.francescoscalise.meteoapp.network

import info.francescoscalise.meteoapp.entity.City
import info.francescoscalise.meteoapp.entity.Country
import info.francescoscalise.meteoapp.entity.State
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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

    suspend fun getAllCountries(): ListCountryResponse {
        return httpClient.get(AIR_VISUAL_ENDPOINT + "countries"){
            parameter("key", AIR_VISUAL_APIKEY)
        }
    }

    suspend fun getStates(country: String): List<State> {
        return httpClient.get(AIR_VISUAL_ENDPOINT + "states"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
        }
    }

    suspend fun getCities(country: String, state: String): List<City> {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "cities"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
            parameter("state", state)
        }
    }

    suspend fun getNearestCity(latitude: Int, longitude: Int): City {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "nearest_city"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("lat", latitude)
            parameter("lon", longitude)
        }
    }

    suspend fun getCity(country: String, state: String, city: String): City {
        return  httpClient.get(AIR_VISUAL_ENDPOINT + "nearest_city"){
            parameter("key", AIR_VISUAL_APIKEY)
            parameter("country", country)
            parameter("state", state)
            parameter("city", city)
        }
    }
}