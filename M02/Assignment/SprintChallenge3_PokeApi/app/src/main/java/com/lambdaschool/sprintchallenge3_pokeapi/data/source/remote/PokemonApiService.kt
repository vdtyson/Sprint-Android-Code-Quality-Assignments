package com.lambdaschool.sprintchallenge3_pokeapi.data.source.remote

import com.lambdaschool.sprintchallenge3_pokeapi.model.Pokemon
import com.lambdaschool.sprintchallenge3_pokeapi.model.PokemonRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonAPIRequest(@Path("id") id: Int ) : Call<Pokemon>

    @GET("/api/v2/pokemon/{name}")
    fun getPokemonAPIRequest(@Path("name") name: String ) : Call<Pokemon>

}