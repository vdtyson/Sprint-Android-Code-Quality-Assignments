package com.lambdaschool.sprintchallenge3_pokeapi.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import com.lambdaschool.sprintchallenge3_pokeapi.NetworkAdapter
import java.io.Serializable

import java.util.ArrayList
// id, moves, name, TypeA, TypeB, sprite get() = NetworkAdapter.getBitmapFromURL(this.spriteUrl!!), spriteUrl
data class Pokemon(
        var id: Int?,
        var moves: String?,
        var name: String?,
        var types: ArrayList<String>?,
        var sprites: ArrayList<String>?
): Serializable {
    val frontDefaultSpriteUrl = sprites?.get(4)

    val typeA: String? = types.let {

        if (it != null) {
            if (it.size >= 1) {
                return@let it[0]
            } else {
                return@let null
            }
        } else return@let null
    }

    val typeB: String? = types.let {
        if (it != null) {
            if (it.size >= 2) {
                return@let it[1]
            } else {
                return@let null
            }
        } else return@let null
    }

    fun spriteBitmap() : Bitmap? {
        return sprites?.get(0)?.let { NetworkAdapter.getBitmapFromURL(it) }
    }

}
