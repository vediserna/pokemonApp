package com.pokemon.app.grpc

interface PokemonApi {
    fun getAll(): Collection<BasicPokemon>
    fun getById(id: Int): ExtendedPokemon
}