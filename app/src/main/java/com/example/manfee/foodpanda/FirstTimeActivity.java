package com.example.manfee.foodpanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstTimeActivity extends AppCompatActivity implements View.OnClickListener {

    Button loginBtn;
    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time);
        loginBtn = findViewById(R.id.login_button);
        signUpBtn = findViewById(R.id.signup_button);
        loginBtn.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                Intent loginIntent = new Intent(FirstTimeActivity.this,LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.signup_button:
                Intent registerIntent = new Intent (FirstTimeActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
                break;
        }

    }


}
