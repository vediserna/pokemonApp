package com.pokemon.app.rest.pokemon

data class ExtendedPokemonBean(
    val id: Int,
    val name: String,
    val abilities: Collection<AbilityBean>,
    val height: Int,
    val weight: Int,
    val moves: Collection<String>,
    val imgPath: String,
    val types: Collection<String>
)

data class AbilityBean(
    val name: String,
    val isHidden: Boolean
)
