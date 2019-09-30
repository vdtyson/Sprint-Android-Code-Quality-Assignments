package com.lambdaschool.notetaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.notetakerroom.R
import kotlinx.android.synthetic.main.activity_edit.*


class EditActivity : AppCompatActivity() {


    internal var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtils.onActivityCreateSetTheme(this)
        setContentView(R.layout.activity_edit)

        note = intent.getSerializableExtra("editNote") as Note
        if (note == null) {
            note = Note(Note.NO_ID)
        }

        edit_title.setText(note!!.title)
        edit_content.setText(note!!.content)
    }

    override fun setTheme(resid: Int) {
        super.setTheme(resid)
    }

    override fun onBackPressed() {
        prepResult()
        super.onBackPressed()
    }

    private fun prepResult() {
        note!!.title = edit_title.text.toString()
        note!!.content = edit_content.text.toString()
        val resultIntent = Intent()
        resultIntent.putExtra(EDIT_NOTE_KEY, note)
        setResult(Activity.RESULT_OK, resultIntent)
    }

    companion object {

        val EDIT_NOTE_KEY = "edit_note"
    }
}
