package com.pokemon.app.grpc

import com.pokemon.app.rest.pokemon.PokemonBean

data class BasicPokemon(
    val name: String,
    val url: String
) {
    fun toPokemonBean() : PokemonBean {
        return PokemonBean(id = getIdFromUrl(), name = this.name)
    }

    private fun getIdFromUrl() : Int {
        val urlComponents = url.split("/")
        return urlComponents.get(urlComponents.lastIndex-1).toInt()
    }
}
