package com.lambdaschool.sprintchallenge3_pokeapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout namesLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        namesLayout = findViewById(R.id.names_list_layout);

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PokemonDetailsActivity.class);
                intent.putExtra("Search_Parameter", ((EditText)findViewById(R.id.search_bar)).getText().toString());
                startActivityForResult(intent, 0);
            }
        });
    }

    private TextView buildTextView(final String name) {
        TextView view = new TextView(this);
        view.setText(name);
        view.setTextSize(14);
        view.setPadding(10, 10, 10, 10);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PokemonDetailsActivity.class);
                intent.putExtra("Search_Parameter", name);
                startActivityForResult(intent, 0);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                namesLayout.removeView(view);
                return true;
            }
        });
        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 0:
                    final Pokemon pokemon = (Pokemon) data.getSerializableExtra("pokemon");
                    if(pokemon != null) {
                        namesLayout.addView(buildTextView(pokemon.getName()));
                    }
                    break;
            }
        }
    }
}
