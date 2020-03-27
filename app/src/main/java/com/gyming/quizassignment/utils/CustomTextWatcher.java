package com.gyming.quizassignment.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.gyming.quizassignment.MainActivity;


public class CustomTextWatcher implements TextWatcher {
    private EditText email;
    private Context context;

    public CustomTextWatcher( EditText email, Context context) {
        this.email = email;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
//email validation
    @Override
    public void afterTextChanged(Editable s) {
        if (!isValidEmail(email.getText().toString())) {
            email.setError("Enter a valid address");
            MainActivity.isValid = false;
        }
        else
        {
            MainActivity.isValid = true;
        }

    }
    public final static boolean isValidEmail(String target) {
        if (target == null) {
            return false;
        } else {
            //android Regex to check the email address Validation
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}