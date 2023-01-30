package com.pokemon.app.repository

import com.pokemon.app.grpc.PokemonApi
import com.pokemon.app.rest.pokemon.ExtendedPokemonBean
import com.pokemon.app.rest.pokemon.PokemonBean

class PokemonRepositoryImpl(private var pokemonApi: PokemonApi) : PokemonRepository {
    override fun getAllPokemon(): List<PokemonBean> {
        return try {
            var basicPokemons = pokemonApi.getAll()

            basicPokemons.map { it.toPokemonBean() }
        } catch (exception : Exception) {
            emptyList()
        }
    }

    override fun getPokemonById(id: Int): ExtendedPokemonBean? {
        return try {
            var pokemonInfo = pokemonApi.getById(id)

            pokemonInfo.toExtendedPokemonBean()
        } catch (exception : Exception) {
            null
        }
    }
}