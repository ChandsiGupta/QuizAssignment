package com.gyming.quizassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gyming.quizassignment.utils.CustomTextWatcher;


public class MainActivity extends AppCompatActivity {
    private EditText nameText, emailText;
    public static boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startButton = findViewById(R.id.button);
        nameText = findViewById(R.id.editName);
        emailText = findViewById(R.id.editEmail);
        //Email Validation through TextWatcher
        emailText.addTextChangedListener(new CustomTextWatcher(emailText,MainActivity.this));
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid) {
                    String name = nameText.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), DisplayQuestionsActivity.class);
                    intent.putExtra("myName", name);
                    startActivity(intent);
                }
            }
        });
    }
}
