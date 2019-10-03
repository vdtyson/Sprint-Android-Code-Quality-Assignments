package com.lambdaschool.sprintchallenge3_pokeapi.model

import com.google.gson.Gson
import retrofit2.converter.gson.GsonConverterFactory

class PokemonRequest {
    var id: Int? = null
    var name: Int? = null

    fun requestPokemon(id: Int) : Int {
        return id
    }
    fun requestPokemon(name: String) : String {
        return name
    }
}