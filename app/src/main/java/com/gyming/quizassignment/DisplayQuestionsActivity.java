package com.gyming.quizassignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gyming.quizassignment.model.Questions;
import com.gyming.quizassignment.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DisplayQuestionsActivity extends AppCompatActivity {
    private TextView questionText, timer, count;
    private Button nextButton, quitButton;
    private RadioGroup radio_g;
    private RadioButton rb1, rb2, rb3, rb4;
    private TextView score;
    private int flag = 0;
    private String ansText;
    int radioId;
    public static int marks = 0, correct = 0, wrong = 0;
    List<Questions> questionsList = new ArrayList<>();
    int countNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_questions);
        init();
        setQuestions();
        setTimer();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radio_g.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton ans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                ansText = ans.getText().toString();
                radioId = radio_g.getCheckedRadioButtonId();
                //correct answer condition
                if (ansText.equals(questionsList.get(flag).getAnswer())) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Your answer is right", Toast.LENGTH_SHORT).show();
                }
                //wrong answer condition
                 else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Your answer is wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText("" + correct);

                if (flag < questionsList.size()) {
                    setQuestions();
                }
                //on ending of test
                else {
                    marks = correct;
                    Intent in = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        //quit button handling
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DisplayResultsActivity.class);
                startActivity(intent);
            }
        });
    }
//retrieve data from assets folder's file question.json

    public void getData() {
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "Questions.json");
        Log.i("data", jsonFileString);
        Type listUserType = new TypeToken<List<Questions>>() {
        }.getType();
        Gson gson = new Gson();
        questionsList = gson.fromJson(jsonFileString, listUserType);
    }
//Initialize views
    public void init() {
        score = findViewById(R.id.textView4);
        TextView textView = findViewById(R.id.DispName);
        Intent intent = getIntent();
        getData();
        String name = intent.getStringExtra("myName");
        textView.setText("Hello " + name);
        nextButton = findViewById(R.id.button_next);
        quitButton = findViewById(R.id.button_quit);
        questionText = findViewById(R.id.tvque);
        radio_g = findViewById(R.id.answersgrp);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        timer = findViewById(R.id.timer);
        count = findViewById(R.id.count);


    }
//to display que
    public void setQuestions() {
        int countText = flag + 1;
        questionText.setText("" + questionsList.get(flag).getQuestion());
        String[] optionsList = questionsList.get(flag).getOptions();
        count.setText(countText + "/" + questionsList.size());
        rb1.setText(optionsList[0]);
        rb2.setText(optionsList[1]);
        rb3.setText(optionsList[2]);
        rb4.setText(optionsList[3]);
    }
//Timer handling
    public void setTimer() {
        new CountDownTimer(5 * 60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("" + String.format("0" + "%d: %d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                timer.setText("done!");
                Toast.makeText(DisplayQuestionsActivity.this, "Oops time out!", Toast.LENGTH_LONG).show();
                timer.setText("done!");
                Intent intent = new Intent(DisplayQuestionsActivity.this, DisplayResultsActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}