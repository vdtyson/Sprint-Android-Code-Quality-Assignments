package com.lambdaschool.sprintchallenge3_pokeapi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal var namesLayout: LinearLayout? = null
    internal var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this
        namesLayout = findViewById(R.id.names_list_layout)

        findViewById<View>(R.id.search_button).setOnClickListener {
            val intent = Intent(context, PokemonDetailsActivity::class.java)
            intent.putExtra("Search_Parameter", (findViewById<View>(R.id.search_bar) as EditText).text.toString())
            startActivityForResult(intent, 0)
        }
    }

    private fun buildTextView(name: String?): TextView {
        val view = TextView(this)
        view.text = name
        view.textSize = 14f
        view.setPadding(10, 10, 10, 10)
        view.setOnClickListener {
            val intent = Intent(context, PokemonDetailsActivity::class.java)
            intent.putExtra("Search_Parameter", name)
            startActivityForResult(intent, 0)
        }
        view.setOnLongClickListener { clickedView ->
            namesLayout?.removeView(clickedView)
            true
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                0 -> {
                    val pokemon = data!!.getSerializableExtra("pokemon") as Pokemon
                    namesLayout?.addView(buildTextView(pokemon.name))
                }
            }
        }
    }
}
