package com.example.billspliter;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AddItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final LinearLayout names_linearLayout = (LinearLayout) findViewById(R.id.names_linearLayout);
        final LinearLayout price_linearLayout = (LinearLayout) findViewById(R.id.price_linearLayout);
        final String[] array_names = getIntent().getStringArrayExtra("array");

        for (int i = 0; i < array_names.length; i++) {
            TextView text = new TextView(AddItems.this);
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            text.setLayoutParams(lparams);
            text.setText(array_names[i]);
            //text.setTextColor(0x000000);
            text.setPadding(20, 35, 20, 20);
            text.setTextSize(18);
            names_linearLayout.addView(text);

            EditText ett = new EditText(AddItems.this);
            ett.setText("");
            price_linearLayout.addView(ett);
        }

        Button btn_next = (Button) findViewById(R.id.btn_next_addItems);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] array_prices = new String[price_linearLayout.getChildCount()];

                for (int i = 0; i < price_linearLayout.getChildCount(); i++) {
                    EditText editText = (EditText) price_linearLayout.getChildAt(i);
                    array_prices[i] = editText.getText().toString();
                }

                String[] array_prices_final = new String[array_names.length];
                for (int i=1; i <= array_names.length; i++){
                    array_prices_final[i-1]=array_prices[i];
                }

                HashMap<String, String> people_map = new HashMap<String, String>();

                for (int i=0; i < array_names.length; i++){
                    people_map.put(array_names[i], array_prices_final[i]);
                }

                EditText bill_total_editText = (EditText) findViewById(R.id.bill_total_editText);
                String bill_total = bill_total_editText.getText().toString();

                Intent intent = new Intent(AddItems.this, Results.class);
                intent.putExtra("people_map", people_map);
                intent.putExtra("bill_total", bill_total);
                startActivity(intent);

            }

        });

    }
}