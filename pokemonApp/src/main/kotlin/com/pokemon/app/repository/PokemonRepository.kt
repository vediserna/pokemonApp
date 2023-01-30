package com.pokemon.app.repository

import com.pokemon.app.rest.pokemon.ExtendedPokemonBean
import com.pokemon.app.rest.pokemon.PokemonBean

interface PokemonRepository {
    fun getAllPokemon(): List<PokemonBean>
    fun getPokemonById(id: Int): ExtendedPokemonBean?
}