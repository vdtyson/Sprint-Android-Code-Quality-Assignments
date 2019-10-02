package com.lambdaschool.sprintchallenge3_pokeapi.model

import android.graphics.Bitmap
import com.lambdaschool.sprintchallenge3_pokeapi.NetworkAdapter
import org.json.JSONException
import org.json.JSONObject

import java.io.Serializable
import java.util.ArrayList

class Pokemon : Serializable {
    var id: Int = 0
        private set
    var moves: ArrayList<String>? = null
        private set
    var name: String? = null
        private set
    var typeA: String? = null
        private set
    var typeB: String? = null
        private set
    private var spriteUrl: String? = null

    val sprite: Bitmap?
        get() = NetworkAdapter.getBitmapFromURL(this.spriteUrl!!)

    constructor(name: String) {
        this.id = 0
        this.moves = ArrayList()
        this.name = name
        this.typeA = ""
        this.typeB = ""
        this.spriteUrl = ""
    }

    constructor(json: JSONObject) {
        try {
            this.id = json.getInt("id")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            this.name = json.getString("name")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            this.spriteUrl = json.getJSONObject("sprites").getString("front_default")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            val typesArray = json.getJSONArray("types")
            this.typeA = typesArray.getJSONObject(0).getJSONObject("type").getString("name")
            this.typeB = typesArray.getJSONObject(1).getJSONObject("type").getString("name")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            this.moves = ArrayList()
            val movesArray = json.getJSONArray("moves")
            for (i in 0 until movesArray.length()) {
                this.moves!!.add(movesArray.getJSONObject(i).getJSONObject("move").getString("name"))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }
}
