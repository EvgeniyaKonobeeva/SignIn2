package com.example.ekonobeeva.signin2;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    public final static int PASSWORD = 0;
    public final static int EMAIL = 1;
    private TextView emailError;
    private EditText passwordField;
    private EditText repeatPasswordField;
    private TextView errorPassword;
    private TextView errorRepeatPassword;
    private EditText emailField;
    private Button checkInBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initEmailField();
        initPasswordField();
        initRepeatPasswordField();

        checkInBut = (Button) findViewById(R.id.check_in_button);


    }

    public boolean isEmailCorrect(){
        String regExEmail = "\\w+@\\w+\\.\\w+";
        if(emailField.getText().toString().matches(regExEmail)){
            passwordField.setEnabled(true);
            return true;
        }
        return false;
    }

    public boolean isPasswordCorrect(){
        if(passwordField.getText().length() < 4){
            repeatPasswordField.setEnabled(false);
            return false;
        }
        repeatPasswordField.setEnabled(true);
        return true;
    }

    public boolean isRepeatPasswordCorrect(){
        String repeatPassword = repeatPasswordField.getText().toString();
        String password = passwordField.getText().toString();
        if(password.equals(repeatPassword)){
            if(isEmailCorrect() && isPasswordCorrect()) {
                checkInBut.setEnabled(true);
            }
            return true;
        }
        checkInBut.setEnabled(false);
        return false;
    }

    public void setEditTextUnderlineColor(View view, int id){
        int color = ContextCompat.getColor(getApplicationContext(), id);
        int blue = Color.blue(color);
        int green = Color.green(color);
        int red = Color.red(color);
        int alpha = Color.alpha(color);

        Drawable drawable = view.getBackground();
        drawable.setColorFilter(Color.argb(alpha, red, green, blue), PorterDuff.Mode.SRC_IN);
        view.setBackground(drawable);
    }

    public class MyFocusChangedListener implements View.OnFocusChangeListener{
        int flag;
        MyFocusChangedListener(int flag){
            this.flag = flag;
        }
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            setField(flag, hasFocus);
        }
    }

    public void setField(int flag, boolean hasFocus){
        switch (flag){
            case PASSWORD :
                Log.d(TAG, "onFocusChange password");
                if(!hasFocus){
                    if (isPasswordCorrect()) {
                        errorPassword.setVisibility(View.GONE);
                        setEditTextUnderlineColor(passwordField,  R.color.editTextNormalColor);
                    } else {
                        errorPassword.setVisibility(View.VISIBLE);
                        setEditTextUnderlineColor(passwordField, R.color.errorColor);
                    }
                }else {
                    setEditTextUnderlineColor(passwordField, R.color.colorAccent);
                    errorPassword.setVisibility(View.GONE);
                }
                break;
            case EMAIL :
                Log.d(TAG, "onFocusChange email");
                if(!hasFocus){
                    if (isEmailCorrect()) {
                        emailError.setVisibility(View.GONE);
                        setEditTextUnderlineColor(emailField,  R.color.editTextNormalColor);
                    } else {
                        emailError.setVisibility(View.VISIBLE);
                        setEditTextUnderlineColor(emailField, R.color.errorColor);
                    }
                }else {
                    setEditTextUnderlineColor(emailField, R.color.colorAccent);
                    emailError.setVisibility(View.GONE);
                }
                break;
        }
    }


    public void initPasswordField(){
        passwordField = (EditText)findViewById(R.id.password_field);
        errorPassword = (TextView)findViewById(R.id.password_error_message);
        errorPassword.setVisibility(View.GONE);
        passwordField.setOnFocusChangeListener(new MyFocusChangedListener(PASSWORD));
        passwordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isPasswordCorrect()) {
                    errorPassword.setVisibility(View.GONE);
                    setEditTextUnderlineColor(passwordField,  R.color.colorAccent);
                } else {
                    errorPassword.setVisibility(View.VISIBLE);
                    setEditTextUnderlineColor(passwordField, R.color.errorColor);
                }
            }
        });
        setEditTextUnderlineColor(passwordField,  R.color.editTextNormalColor);
    }

    public void initRepeatPasswordField(){
        repeatPasswordField = (EditText)findViewById(R.id.repeat_password_field);
        errorRepeatPassword = (TextView)findViewById(R.id.repeat_password_error_message);
        errorRepeatPassword.setVisibility(View.GONE);
        setEditTextUnderlineColor(repeatPasswordField, R.color.editTextNormalColor);
        repeatPasswordField.setEnabled(false);
        repeatPasswordField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (isRepeatPasswordCorrect()) {
                    errorRepeatPassword.setVisibility(View.GONE);
                    setEditTextUnderlineColor(repeatPasswordField,  R.color.colorAccent);
                } else {
                    errorRepeatPassword.setVisibility(View.VISIBLE);
                    setEditTextUnderlineColor(repeatPasswordField, R.color.errorColor);
                }
            }
        });
        repeatPasswordField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(hasFocus){
                    setEditTextUnderlineColor(repeatPasswordField, R.color.colorAccent);
                }else{
                    if(isRepeatPasswordCorrect())
                        setEditTextUnderlineColor(repeatPasswordField, R.color.editTextNormalColor);
                }
            }
        });
    }

    public void initEmailField(){
        emailError = (TextView) findViewById(R.id.email_error_message);
        emailError.setVisibility(View.GONE);
        emailField = (EditText)findViewById(R.id.email_field);
        emailField.setOnFocusChangeListener(new MyFocusChangedListener(EMAIL));
        setEditTextUnderlineColor(emailField,  R.color.editTextNormalColor);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
