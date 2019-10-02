package com.lambdaschool.sprintchallenge3_pokeapi.source.remote

import com.lambdaschool.sprintchallenge3_pokeapi.PokemonDao
import com.lambdaschool.sprintchallenge3_pokeapi.model.Pokemon
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("/api/v2/pokemon/{id}")
    fun getPokemonByIDApiCall(@Path("id") id: Int ) : Call<Pokemon>
}