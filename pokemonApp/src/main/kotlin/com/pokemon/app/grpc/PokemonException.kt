package com.pokemon.app.grpc

class PokemonException (
    override val message: String,
    val statusCode: Int
) : RuntimeException()