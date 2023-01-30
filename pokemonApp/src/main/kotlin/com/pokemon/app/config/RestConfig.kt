package com.pokemon.app.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(PokemonConfig::class)
class RestConfig