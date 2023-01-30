package com.pokemon.app.repository

import com.pokemon.app.grpc.Ability
import com.pokemon.app.grpc.BasicPokemon
import com.pokemon.app.grpc.ExtendedPokemon
import com.pokemon.app.grpc.ImagePath
import com.pokemon.app.grpc.Move
import com.pokemon.app.grpc.PokemonApi
import com.pokemon.app.grpc.PokemonException
import com.pokemon.app.grpc.Type
import com.pokemon.app.grpc.TypeName
import com.pokemon.app.rest.pokemon.AbilityBean
import com.pokemon.app.rest.pokemon.ExtendedPokemonBean
import com.pokemon.app.rest.pokemon.PokemonBean
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

private const val DEFAULT_NAME = "pikachu"
private const val DEFAULT_URL = "https://pokeapi.co/api/v2/pokemon/172/"

class PokemonRepositoryTest {
    private val pokemonApi: PokemonApi = mock()
    private val underTest: PokemonRepository = PokemonRepositoryImpl(pokemonApi)

    private val basicPokemon = BasicPokemon(name = DEFAULT_NAME, url = DEFAULT_URL)
    private val extendedPokemon = ExtendedPokemon(
        id = 172,
        name = DEFAULT_NAME,
        abilities = listOf(
            Ability(
                ability = mapOf("name" to "static"),
                isHidden = false
            ),
            Ability(
                ability = mapOf("name" to "lightning-rod"),
                isHidden = true
            )
        ),
        height = 3,
        weight = 20,
        moves = listOf(
            Move(move = mapOf("name" to "double-slap")),
            Move(move = mapOf("name" to "mega-punch")),
            Move(move = mapOf("name" to "thunder-punch"))
        ),
        imgPath = ImagePath(path = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/172.png"),
        types = listOf(Type(type = TypeName(name = "electric")))
    )

    @Test
    fun `should return empty list if api call throws an error`() {
        whenever(pokemonApi.getAll())
            .thenThrow(PokemonException::class.java)

        val result = underTest.getAllPokemon()

        assert(result.isEmpty())
    }

    @Test
    fun `should return a list of pokemon beans`() {
        val expectedResult = listOf(PokemonBean(name = DEFAULT_NAME, id = 172))
        whenever(pokemonApi.getAll())
            .thenReturn(listOf(basicPokemon))

        val result = underTest.getAllPokemon()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `should call the api with the given id`() {
        val id = 172
        whenever(pokemonApi.getById(id))
            .thenReturn(extendedPokemon)

        underTest.getPokemonById(id)

        verify(pokemonApi, times(1)).getById(id)
    }

    @Test
    fun `should return an extended pokemon bean`() {
        val id = 172
        val expectedResult = ExtendedPokemonBean(
            id = id,
            name = DEFAULT_NAME,
            abilities = listOf(
                AbilityBean(
                    name = "static",
                    isHidden = false
                ),
                AbilityBean(
                    name = "lightning-rod",
                    isHidden = true
                )
            ),
            height = 3,
            weight = 20,
            moves = listOf("double-slap", "mega-punch", "thunder-punch"),
            imgPath = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/172.png",
            types = listOf("electric")
        )
        whenever(pokemonApi.getById(id))
            .thenReturn(extendedPokemon)

        val result = underTest.getPokemonById(id)

        assertEquals(expectedResult, result)
    }
}