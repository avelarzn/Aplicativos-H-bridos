package com.example.desafio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.Nullable;


public class MainActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText moneyInput = findViewById(R.id.input_money);
                DatePicker datePicker = findViewById(R.id.date_picker);
                EditText percentageInput = findViewById(R.id.input_percentage);

                String money = moneyInput.getText().toString();
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;
                String percentage = percentageInput.getText().toString();

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("money", money);
                intent.putExtra("date", date);
                intent.putExtra("percentage", percentage);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}