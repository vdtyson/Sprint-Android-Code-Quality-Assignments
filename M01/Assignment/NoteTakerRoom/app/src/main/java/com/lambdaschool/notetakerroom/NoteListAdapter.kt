package com.lambdaschool.notetaker

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.notetakerroom.R

import java.util.ArrayList

class NoteListAdapter internal constructor(private val dataList: ArrayList<Note>, private val activity: Activity) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    private var context: Context? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var noteContent: TextView
        var parentView: ViewGroup

        init {

            noteContent = itemView.findViewById(R.id.note_element_content)
            parentView = itemView.findViewById(R.id.note_element_parent_layout)
        }
    }

    fun replaceList(newData: ArrayList<Note>) {
        this.dataList.clear()
        this.dataList.addAll(newData)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        context = viewGroup.context
        val view = LayoutInflater.from(
                viewGroup.context)
                .inflate(
                        R.layout.note_element_layout,
                        viewGroup,
                        false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val data = dataList[i]

        viewHolder.noteContent.text = data.content
        viewHolder.parentView.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra(EditActivity.EDIT_NOTE_KEY, data)
            activity.startActivityForResult(intent, MainActivity.EDIT_REQUEST_CODE)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
