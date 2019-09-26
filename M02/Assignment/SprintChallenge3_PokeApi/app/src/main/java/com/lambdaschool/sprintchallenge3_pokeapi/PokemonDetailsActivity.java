package com.lambdaschool.sprintchallenge3_pokeapi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PokemonDetailsActivity extends AppCompatActivity {

    TextView textName, textTypeA, textTypeB, textId;
    LinearLayout movesListLayout;
    ImageView image;

    Pokemon pokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        textName = findViewById(R.id.text_title);
        textTypeA = findViewById(R.id.type_A);
        textTypeB = findViewById(R.id.type_B);
        textId = findViewById(R.id.text_number);
        movesListLayout = findViewById(R.id.layout_moves_list);
        image = findViewById(R.id.background_image);

        new GetPokemon().execute(getIntent().getStringExtra("Search_Parameter"));
    }

    private TextView buildTextView(final String moveName) {
        TextView view = new TextView(this);
        view.setText(moveName);
        view.setTextSize(14);
        view.setPadding(10, 10, 10, 10);
        return view;
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_OK, (new Intent().putExtra("pokemon", pokemon)));
        finish();
    }

    public class GetPokemon extends AsyncTask<String, Pokemon, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            image.setImageBitmap(bitmap);
            findViewById(R.id.progress_circular).setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(Pokemon... values) {
            pokemon = values[0];
            textName.setText(values[0].getName());
            textTypeA.setText(values[0].getTypeA());
            textTypeB.setText(values[0].getTypeB());
            textId.setText(String.format("No %03d", values[0].getId()));

            final ArrayList<View> movesViews = new ArrayList<>();
            for(String move: values[0].getMoves()) {
                movesViews.add(buildTextView(move));
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for(View moveView: movesViews) {
                        movesListLayout.addView(moveView);
                    }
                }
            });
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Pokemon loading = PokemonDao.getPokemon(strings[0]);
            onProgressUpdate(loading);
            return loading.getSprite();
        }
    }
}
