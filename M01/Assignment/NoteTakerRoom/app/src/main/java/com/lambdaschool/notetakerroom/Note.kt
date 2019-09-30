package com.lambdaschool.notetaker

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject

import java.io.Serializable

@Entity
class Note : Serializable {

    var title: String? = null
    var content: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    var timestamp: Long = 0
        private set

    constructor(id: Long, title: String, content: String) {
        this.title = title
        this.content = content
        this.id = id
        this.timestamp = System.currentTimeMillis()
    }

    constructor(title: String, content: String, id: String, timestamp: Long) {
        this.title = title
        this.content = content
        this.id = id.toLongOrNull() ?: 0
        this.timestamp = timestamp
    }

    constructor(id: String, title: String, content: String) {
        this.title = title
        this.content = content
        this.id = id.toLongOrNull() ?: 0
        this.timestamp = System.currentTimeMillis()
    }

    /*public Note(String csvString) {
        String[] values = csvString.split(",");
        this.title = values[0];
        this.content = values[1];
        this.id = values[2];

        toCsvString();
    }*/

    constructor(id: String) {
        this.id = id.toLongOrNull() ?: 0
        this.timestamp = System.currentTimeMillis()
    }

    constructor(id: Long) {
        this.id = id
    }

    constructor(jsonObject: JSONObject, name: String) {
        this.id = name.toLongOrNull() ?: 0

        try {
            this.content = jsonObject.getString("content")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            this.title = jsonObject.getString("title")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            this.timestamp = jsonObject.getLong("timestamp")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun toCsvString(): String {
        return this.title!!.replace(",".toRegex(), "") + "," + this.content!!.replace(",".toRegex(), "") + "," + this.id
    }

    fun toJsonString(): String {
        val json = JSONObject()
        try {
            json.put("title", this.title)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            json.put("content", this.content)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        try {
            json.put("timestamp", this.timestamp)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return json.toString()
    }

    companion object {
        val NO_ID = ""
    }
}
