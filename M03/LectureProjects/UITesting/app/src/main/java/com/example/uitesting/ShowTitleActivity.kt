package com.example.uitesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_title.*

class ShowTitleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_title)

        show_title_view.text = intent.getStringExtra(MainActivity.KEY_TITLE) ?: "No Dice!"
    }
}
