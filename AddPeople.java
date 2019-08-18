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

import java.util.HashMap;
import java.util.Map;

public class AddPeople extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
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

        final  LinearLayout ll = (LinearLayout)findViewById(R.id.linear_layout);

        Button btn_addPerson = (Button)findViewById(R.id.btn_addPerson);
        btn_addPerson.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //adds EditText for name input
                EditText ett = new EditText(AddPeople.this);
                ett.setText("Name");
                ll.addView(ett);
            }
        });

        Button btn_next = (Button)findViewById(R.id.btn_next_addItems);
        btn_next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String[] array = new String[ll.getChildCount()];

                //adds names to array
                for (int i=0; i < ll.getChildCount(); i++){
                    EditText editText = (EditText)ll.getChildAt(i);
                    array[i] = editText.getText().toString();
                }

                //passes array to next activity
                Intent intent = new Intent(AddPeople.this, AddItems.class);
                intent.putExtra("array", array);
                startActivity(intent);
            }
        });
    }
}
