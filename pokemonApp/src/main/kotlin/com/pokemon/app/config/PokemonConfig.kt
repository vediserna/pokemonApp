package com.pokemon.app.config

import com.pokemon.app.grpc.PokemonApi
import com.pokemon.app.grpc.PokemonApiImpl
import com.pokemon.app.repository.PokemonRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PokemonConfig {
    @Bean
    fun PokemonApi() : PokemonApi = PokemonApiImpl()

    @Bean
    fun pokemonRepository(pokemonApi: PokemonApi) = PokemonRepositoryImpl(pokemonApi)
}