package com.pokemon.app.grpc

import com.pokemon.app.rest.pokemon.AbilityBean
import com.pokemon.app.rest.pokemon.ExtendedPokemonBean
import com.google.gson.annotations.SerializedName

data class ExtendedPokemon(
    val id: Int,
    val abilities: Collection<Ability>,
    val height: Int,
    val weight: Int,
    val moves: Collection<Move>,
    val name: String,
    @field:SerializedName("sprites")
    val imgPath: ImagePath,
    val types: Collection<Type>
) {
    fun toExtendedPokemonBean(): ExtendedPokemonBean {
        return ExtendedPokemonBean(
            id = id,
            name = name,
            abilities = abilities.map { AbilityBean(name = it.getAbilityName(), isHidden = it.isHidden) },
            height = height,
            weight = weight,
            moves = moves.map { it.getMoveName() },
            imgPath = imgPath.path,
            types = types.map { it.type.name }
        )
    }
}

data class Ability (
    val ability: Map<String, String>,
    @field:SerializedName("is_hidden")
    val isHidden: Boolean
) {
    fun getAbilityName(): String { return ability["name"] ?: "" }
}

data class Move (
    val move: Map<String, String>
) {
    fun getMoveName(): String {
        return move["name"] ?: ""
    }
}

data class ImagePath(
    @field:SerializedName("front_default")
    val path: String
)

data class Type(
    val type: TypeName
)

data class TypeName(
    val name: String
)
