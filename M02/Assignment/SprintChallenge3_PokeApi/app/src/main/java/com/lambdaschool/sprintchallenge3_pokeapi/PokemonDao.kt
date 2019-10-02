package com.lambdaschool.sprintchallenge3_pokeapi

import com.lambdaschool.sprintchallenge3_pokeapi.model.Pokemon
import org.json.JSONException
import org.json.JSONObject

object PokemonDao {
    private val POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/%s/" // add pokemon name as

    fun getPokemon(name: String): Pokemon {
        var pokemon: Pokemon
        try {
            pokemon = Pokemon(JSONObject(NetworkAdapter.httpRequest(String.format(POKEMON_URL, name), "GET")))
        } catch (e: JSONException) {
            e.printStackTrace()
            pokemon = Pokemon(name)
        }

        return pokemon
    }
}
