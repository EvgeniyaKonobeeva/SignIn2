package com.example.ekonobeeva.signin2;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView emailError = (TextView) findViewById(R.id.Email_ErrorMessage);
        emailError.setVisibility(View.INVISIBLE);

        final EditText emailField = (EditText)findViewById(R.id.email_Field);
        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                Log.d(TAG, "onFocusChange");
                if(!hasFocus){
                    if (isEmailCorrect(emailField.getText().toString())) {
                        emailError.setVisibility(View.INVISIBLE);
                        emailField.getBackground().clearColorFilter();

                    } else {
                        emailError.setVisibility(View.VISIBLE);
                        emailField.setBottom(Color.BLUE);
                    }
                }else{

                }
            }
        });
    }

    public static boolean isEmailCorrect(String text){
        String regExEmail = "\\w+@\\w+\\.\\w+";
        if(text.matches(regExEmail) || text.isEmpty()){
            Log.d(TAG, "correct " + text);
            return true;
        }
        return false;
    }


}
