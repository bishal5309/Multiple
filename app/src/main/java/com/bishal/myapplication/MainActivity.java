package com.bishal.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    TextView text;
    int i = 2;

    private Handler handler = new Handler();
    private Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = findViewById(R.id.et1);

        text = findViewById(R.id.text);


        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String number = s.toString().trim();


                if (number.isEmpty()) {
                    text.setText("Enter the number for which you want the multiples:");
                    if (runnable != null) {
                        handler.removeCallbacks(runnable);
                    }
                    return;
                }

                try {
                    int inputNumber = Integer.parseInt(number);
                    if (inputNumber <= 0) {
                        text.setText("Please enter a positive number.");
                        if (runnable != null) {
                            handler.removeCallbacks(runnable);
                        }
                        return;
                    }


                    StringBuilder multiples = new StringBuilder();
                    for (int i = 1; i <= 10; i++) {
                        multiples.append(i).append("  X  ").append(inputNumber).append("  =  ").append(i * inputNumber).append("\n");
                    }


                    if (runnable != null) {
                        handler.removeCallbacks(runnable);
                    }


                    showMultiplesWithAnimation(text, multiples.toString());
                } catch (NumberFormatException e) {
                    text.setText("Invalid input! Please enter numeric values.");
                    if (runnable != null) {
                        handler.removeCallbacks(runnable);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });






    }

    private void showMultiplesWithAnimation(TextView textView, String text) {
        textView.setText("");
        final int length = text.length();
        final int delay = 50;
        final int[] index = {0};


        runnable = new Runnable() {
            @Override
            public void run() {
                if (index[0] <= length) {
                    textView.setText(text.substring(0, index[0]));
                    index[0]++;
                    handler.postDelayed(this, delay);
                }
            }
        };

        handler.postDelayed(runnable, delay);
    }



}