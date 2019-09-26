package com.lambdaschool.sprintchallenge3_pokeapi

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_pokemon_details.*

import java.util.ArrayList

class PokemonDetailsActivity : AppCompatActivity() {

    internal var pokemon: Pokemon? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        GetPokemon().execute(intent.getStringExtra("Search_Parameter"))
    }

    private fun buildTextView(moveName: String): TextView {
        val view = TextView(this)
        view.text = moveName
        view.textSize = 14f
        view.setPadding(10, 10, 10, 10)
        return view
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK, Intent().putExtra("pokemon", pokemon))
        finish()
    }

    inner class GetPokemon : AsyncTask<String, Pokemon, Bitmap>() {

        override fun onPostExecute(bitmap: Bitmap) {
            background_image?.setImageBitmap(bitmap)
            findViewById<View>(R.id.progress_circular).visibility = View.INVISIBLE
        }

        override fun onProgressUpdate(vararg values: Pokemon) {
            pokemon = values[0]
            text_title?.text = values[0].name
            type_A?.text = values[0].typeA
            type_B?.text = values[0].typeB
            text_number?.text = String.format("No %03d", values[0].id)

            val movesViews = ArrayList<View>()
            for (move in values[0].moves!!) {
                movesViews.add(buildTextView(move))
            }
            runOnUiThread {
                for (moveView in movesViews) {
                    layout_moves_list?.addView(moveView)
                }
            }
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg strings: String): Bitmap? {
            val loading = PokemonDao.getPokemon(strings[0])
            onProgressUpdate(loading)
            return loading.sprite
        }
    }
}
