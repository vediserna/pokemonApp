package com.pokemon.app.rest

import com.pokemon.app.grpc.ExtendedPokemon
import com.pokemon.app.repository.PokemonRepository
import com.pokemon.app.rest.pokemon.ExtendedPokemonBean
import com.pokemon.app.rest.pokemon.PokemonBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
class PokemonResource @Autowired constructor(
    private val pokemonRepository: PokemonRepository
) {
    @GetMapping("/")
    fun getAllPokemons(): List<PokemonBean> {
        return pokemonRepository.getAllPokemon();
    }

    @GetMapping("/{id}")
    fun getPokemonById(@PathVariable id: Int): ExtendedPokemonBean? {
        return pokemonRepository.getPokemonById(id);
    }
}