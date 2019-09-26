package com.lambdaschool.sprintchallenge3_pokeapi;

import android.graphics.Bitmap;
import android.media.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Pokemon implements Serializable {
    private int               id;
    private ArrayList<String> moves;
    private String            name, typeA, typeB, spriteUrl;

    public Pokemon(String name) {
        this.id = 0;
        this.moves = new ArrayList<>();
        this.name = name;
        this.typeA = "";
        this.typeB = "";
        this.spriteUrl = "";
    }

    public Pokemon(JSONObject json) {
        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.name = json.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.spriteUrl = json.getJSONObject("sprites").getString("front_default");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray typesArray = json.getJSONArray("types");
            this.typeA = typesArray.getJSONObject(0).getJSONObject("type").getString("name");
            this.typeB = typesArray.getJSONObject(1).getJSONObject("type").getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.moves = new ArrayList<>();
            JSONArray movesArray = json.getJSONArray("moves");
            for (int i = 0; i < movesArray.length(); ++i) {
                this.moves.add(movesArray.getJSONObject(i).getJSONObject("move").getString("name"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }

    public String getTypeA() {
        return typeA;
    }

    public String getTypeB() {
        return typeB;
    }

    public Bitmap getSprite() {
        return NetworkAdapter.getBitmapFromURL(this.spriteUrl);
    }
}
