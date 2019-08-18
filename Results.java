package com.example.billspliter;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
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

        final HashMap<String, String> people_map = (HashMap<String, String>)getIntent().getSerializableExtra("people_map");
        final String bill_total_str = getIntent().getStringExtra("bill_total");

        double sub_total = 0;
        for (String value : people_map.values()) {
            sub_total += Double.parseDouble(value);
        }
        System.out.println(sub_total);
        System.out.println(people_map.size());

        double bill_total_double = Double.parseDouble(bill_total_str);

        double diff = bill_total_double - sub_total;

        double extra_cost_per = diff / people_map.size();

        for (String name : people_map.keySet()) {
            double cost = (Double.parseDouble(people_map.get(name)) + extra_cost_per);
            String cost_str = Double.toString(cost);
            people_map.put(name, cost_str);
        }
        //TODO algor for dividing up bill is above. Need to put these vales on to screen.
        //  - use similar set to to add items activity where LHS is name and RHS is cost for each
        //  - fix y hashmap isnt getting passed

        final LinearLayout names_layout = (LinearLayout) findViewById(R.id.resutls_names_layout);
        final LinearLayout cost_layout = (LinearLayout) findViewById(R.id.cost_per_layout);

        for (String name : people_map.keySet()){
            TextView name_TV = new TextView(Results.this);
            LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            name_TV.setLayoutParams(lparams);
            name_TV.setText(name);
            name_TV.setPadding(20, 35, 20, 20);
            name_TV.setTextSize(18);
            names_layout.addView(name_TV);

            TextView cost_per = new TextView(Results.this);
            cost_per.setLayoutParams(lparams);
            cost_per.setText("$" + people_map.get(name));
            cost_per.setPadding(20, 35, 20, 20);
            cost_per.setTextSize(18);
            cost_layout.addView(cost_per);
        }




    }

}
