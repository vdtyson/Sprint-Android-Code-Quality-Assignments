package com.lambdaschool.sprintchallenge3_pokeapi

import com.lambdaschool.sprintchallenge3_pokeapi.data.source.remote.PokemonRepo
import com.lambdaschool.sprintchallenge3_pokeapi.model.Pokemon
import org.json.JSONException
import org.json.JSONObject

object PokemonDao {
    fun getPokemon(id: Int): Pokemon {
        val pokemonRepo = PokemonRepo()
        var pokemon: Pokemon

        pokemon = pokemonRepo.getPokemonFromAPI(id)!!

        return pokemon
    }
    fun getPokemon(name: String): Pokemon? {
        val pokemonRepo = PokemonRepo()
        var pokemon: Pokemon?

        pokemon = pokemonRepo.getPokemonFromAPI(name)

        return pokemon
    }
}
