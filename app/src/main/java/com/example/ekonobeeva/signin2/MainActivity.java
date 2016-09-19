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
    public final static int REPEAT_PASSWORD = 2;
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

    public boolean isEmailCorrect(String text){
        String regExEmail = "\\w+@\\w+\\.\\w+";
        if(text.matches(regExEmail)){
            Log.d(TAG, "correct " + text);
            passwordField.setEnabled(true);
            return true;
        }
        //passwordField.setEnabled(false);
        return false;
    }

    public boolean isPasswordCorrect(String text){
        if(text.length() < 4){
            repeatPasswordField.setEnabled(false);
            return false;
        }
        repeatPasswordField.setEnabled(true);
        return true;
    }

    public boolean isRepeatPasswordCorrect(String password, String repeatPassword){
        if(password.equals(repeatPassword)){
            checkInBut.setEnabled(true);
            return true;
        }
        checkInBut.setEnabled(false);
        return false;
    }

    public void setFieldColor(View view, int id){
        int color = ContextCompat.getColor(getApplicationContext(), id);
        int blue = Color.blue(color);
        int green = Color.green(color);
        int red = Color.red(color);
        int alpha = Color.alpha(color);

        Drawable drawable = view.getBackground();
        drawable.setColorFilter(Color.argb(alpha, red, green, blue), PorterDuff.Mode.SRC_IN);
        view.setBackground(drawable);
    }

    public class EmailFieldFocusChangedListener implements View.OnFocusChangeListener{
        int flag;
        EmailFieldFocusChangedListener(int flag){
            this.flag = flag;
        }
        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            Log.d(TAG, "onFocusChange");
            setField(flag, hasFocus);
        }
    }

    public void setField(int flag, boolean hasFocus){
        switch (flag){
            case PASSWORD :
                if(!hasFocus){
                    if (isPasswordCorrect(passwordField.getText().toString())) {
                        errorPassword.setVisibility(View.INVISIBLE);
                        setFieldColor(passwordField,  R.color.editTextNormalColor);
                    } else {
                        errorPassword.setVisibility(View.VISIBLE);
                        setFieldColor(passwordField, R.color.errorColor);
                    }
                }else {
                    setFieldColor(passwordField, R.color.colorAccent);
                }
                break;
            case EMAIL :
                if(!hasFocus){
                    if (isEmailCorrect(emailField.getText().toString())) {
                        emailError.setVisibility(View.INVISIBLE);
                        setFieldColor(emailField,  R.color.editTextNormalColor);
                    } else {
                        emailError.setVisibility(View.VISIBLE);
                        setFieldColor(emailField, R.color.errorColor);
                    }
                }else {
                    setFieldColor(emailField, R.color.colorAccent);
                }
                break;
        }
    }


    public void initPasswordField(){
        passwordField = (EditText)findViewById(R.id.password_field);
        errorPassword = (TextView)findViewById(R.id.password_error_message);
        errorPassword.setVisibility(View.INVISIBLE);
        passwordField.setOnFocusChangeListener(new EmailFieldFocusChangedListener(PASSWORD));
        setFieldColor(passwordField,  R.color.editTextNormalColor);
    }

    public void initRepeatPasswordField(){
        repeatPasswordField = (EditText)findViewById(R.id.repeat_password_field);
        errorRepeatPassword = (TextView)findViewById(R.id.repeat_password_error_message);
        errorRepeatPassword.setVisibility(View.INVISIBLE);
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
                if (isRepeatPasswordCorrect(passwordField.getText().toString(), repeatPasswordField.getText().toString())) {
                    errorRepeatPassword.setVisibility(View.INVISIBLE);
                    setFieldColor(repeatPasswordField,  R.color.colorAccent);
                } else {
                    errorRepeatPassword.setVisibility(View.VISIBLE);
                    setFieldColor(repeatPasswordField, R.color.errorColor);
                }
            }
        });
    }

    public void initEmailField(){
        emailError = (TextView) findViewById(R.id.email_error_message);
        emailError.setVisibility(View.INVISIBLE);
        emailField = (EditText)findViewById(R.id.email_field);
        emailField.setOnFocusChangeListener(new EmailFieldFocusChangedListener(EMAIL));
        setFieldColor(emailField,  R.color.editTextNormalColor);
    }
}
