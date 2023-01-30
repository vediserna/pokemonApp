package com.pokemon.app.grpc

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class PokemonApiImpl: PokemonApi {
    private val baseUrl = "https://pokeapi.co/api/v2/"
    private val gson: Gson = Gson()
    private val client: HttpClient = HttpClient.newBuilder().build()

    override fun getAll(): Collection<BasicPokemon> {
        val allPokemons: ArrayList<BasicPokemon> = ArrayList()
        val type = genericType<PaginatedResult<BasicPokemon>>()

        var url: String? = buildUrl("pokemon", emptyMap())

        do {
            var responseData = makeGetRequest(url!!)
            val paginatedResult: PaginatedResult<BasicPokemon> = gson.fromJson(responseData, type)
            allPokemons.addAll(paginatedResult.results)
            url = paginatedResult.next
        } while (url != null)

        return allPokemons
    }

    override fun getById(id: Int): ExtendedPokemon {
        var url: String = buildUrl("pokemon/${id}", emptyMap())
        var responseData = makeGetRequest(url)

        return gson.fromJson(responseData, ExtendedPokemon::class.java)
    }

    inline fun <reified T> genericType() = object: TypeToken<T>() {}.type

    private fun makeGetRequest(url: String): String? {
        val request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        if (response.statusCode() >= 300) {
            throw PokemonException(message = "Not able to get data from Pokemon API", statusCode = response.statusCode())
        }

        return response.body()
    }

    private fun buildUrl(path: String, params: Map<String, String>): String {
        var url = "$baseUrl$path"

        if (params.isNotEmpty()) {
            val paramsString = params.map {(k, v) -> "${k}=${v}"}
                .joinToString("&")
            url = url.plus("?").plus(paramsString)
        }

        return url
    }
}