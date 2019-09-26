package com.lambdaschool.sprintchallenge3_pokeapi;

import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDao {
    private static final String POKEMON_URL = "https://pokeapi.co/api/v2/pokemon/%s/"; // add pokemon name as

    public static Pokemon getPokemon(String name) {
        Pokemon pokemon;
        try {
            pokemon = new Pokemon(new JSONObject(NetworkAdapter.httpRequest(String.format(POKEMON_URL, name), "GET")));
        } catch (JSONException e) {
            e.printStackTrace();
            pokemon = new Pokemon(name);
        }
        return pokemon;
    }
}
