package com.lambdaschool.sprintchallenge3_pokeapi.data.source.remote

import com.lambdaschool.sprintchallenge3_pokeapi.model.Pokemon
import com.lambdaschool.sprintchallenge3_pokeapi.model.PokemonRequest
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonRepo {
    val pokemonApiService = PokemonApiFactory.create()
    lateinit var pokemon: Pokemon

    fun getPokemonFromAPI(searchParameter: String) : Pokemon? {

        var pokemon: Pokemon? = null
        val pokemonRequest = PokemonRequest()
        val requestByName = pokemonRequest.requestPokemon(searchParameter)
        pokemonApiService.getPokemonAPIRequest(requestByName).enqueue(object : Callback<Pokemon> {

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {

            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if(response.isSuccessful && response.body() != null) {

                    try {
                        response.body()?.let {
                            pokemon = Pokemon(it.id,it.moves,it.name,it.types,it.sprites)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }
        })

        return pokemon
    }
    fun getPokemonFromAPI(searchParameter: Int) : Pokemon? {

        var pokemon: Pokemon? = null
        val pokemonRequest = PokemonRequest()
        val requestById = pokemonRequest.requestPokemon(searchParameter)
        pokemonApiService.getPokemonAPIRequest(requestById).enqueue(object : Callback<Pokemon> {

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {

            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                if(response.isSuccessful && response.body() != null) {
                    try {
                        response.body()?.let {
                            pokemon = Pokemon(it.id,it.moves,it.name,it.types,it.sprites)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }

        })
        return pokemon
    }
}