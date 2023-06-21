package com.app.hatim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profileActivity extends AppCompatActivity {
    TextView _profile_userName,_sp_userName;
    String username;

    Button _btnLogout;

    public static final String myPref= "myPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        _profile_userName = findViewById(R.id.profile_userName);
        _sp_userName = findViewById(R.id.sp_userName);
        _btnLogout = findViewById(R.id.btnLogout);

        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(i.hasExtra("username"))
        {
            username = b.getString("username");
            _profile_userName.setText("Welcome ,"+username); //To edit any text during runtime from java code we set variableName.setText(text)
        }



        String sp_uname = sharedPreferences.getString("username","");

        _sp_userName.setText("Shared Preference User Name: " + sp_uname);

        _btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutPreference();

                Intent ii = new Intent(profileActivity.this,loginActivity.class);
                startActivity(ii);
                finish();
            }
        });


    }

    protected void logoutPreference()
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.putString("username","");
        editor.clear();
        editor.commit();
    }
}