package com.app.hatim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {
    TextView _profile_userName;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _profile_userName = findViewById(R.id.profile_userName);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        username = b.getString("username");

        _profile_userName.setText("Welcome ,"+username); //To edit any text during runtime from java code we set variableName.setText(text)

    }
}