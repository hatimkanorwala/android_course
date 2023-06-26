package com.app.hatim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class loginActivity extends AppCompatActivity {
    EditText _userName,_password;
    Button _btnSubmit;
    String username,password;
    TextView _contactDeveloper,_visitWebsite,_sendEmail;

    public static final String myPref= "myPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _userName = findViewById(R.id.userName);
        _password = findViewById(R.id.password);
        _btnSubmit = findViewById(R.id.btnSubmit);
        _contactDeveloper = findViewById(R.id.contactDeveloper);
        _visitWebsite = findViewById(R.id.visitWebsite);
        _sendEmail = findViewById(R.id.sendEmail);


        sharedPreferences = getSharedPreferences(myPref, Context.MODE_PRIVATE);

        String check = checkUserLogin();
        if(check != "" || check.length() > 0)
        {
            Intent red = new Intent(loginActivity.this,profileActivity.class);
            startActivity(red);
            finish();
        }


        _btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = _userName.getText().toString().trim();
                password= _password.getText().toString().trim();

                Log.e("UserName",username);
                Log.e("Password",password);

                if(username.equals("hatimkanorwala") && password.equals("Hatim@123"))
                {
                    //To Display the login message to user
                    Snackbar snackbar = Snackbar.make(getCurrentFocus(),"UserName: " + username,Snackbar.LENGTH_LONG);
                    snackbar.show();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.commit();

                    //To move from one page to another
                    Intent i = new Intent(loginActivity.this, profileActivity.class);
                    i.putExtra("username",username);
                    startActivity(i);
                    finish(); //To destroy the current process and execute only the new process

                }
                else
                {
                    //Toast.makeText(loginActivity.this,"Failed to Login",Toast.LENGTH_SHORT).show();

                    //Alert Dialog Box to perform some action
                    //displayAlert();

                    //Alert Dialog Box with customized Message
                    customizedMessageDialogBox();
                }




            }
        });

        _contactDeveloper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(Intent.ACTION_DIAL);
                ii.setData(Uri.parse("tel:+918097922009"));
                startActivity(ii);
            }
        });

        String website = "https://google.com";
        _visitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(website));
                startActivity(web);
            }
        });

        String subject = "This email is from Android App";
        String body = "Hello, \nThis email is been sent from android app using Intent";

        _sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"));
                m.putExtra(Intent.EXTRA_SUBJECT,subject);
                m.putExtra(Intent.EXTRA_TEXT,body);
                m.putExtra(Intent.EXTRA_HTML_TEXT,body); //It is used if our body text contains html tags
                startActivity(Intent.createChooser(m,"Choose App to Send Email"));
            }
        });

    }

    private void customizedMessageDialogBox() {
        AlertDialog.Builder builder =new AlertDialog.Builder(loginActivity.this);
        builder.setCancelable(false);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.popupmessage,null);
        TextView _btnClose = view.findViewById(R.id.btnClose);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        _btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private String checkUserLogin() {
        String uname = sharedPreferences.getString("username","");
        return uname;
    }

    protected void displayAlert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
        builder.setTitle("Fail");
        builder.setMessage("Login Failed, Enter Proper Details");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.error_icon);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
//                    builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(loginActivity.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Toast.makeText(loginActivity.this, "Neutral Button Cliked", Toast.LENGTH_SHORT).show();
//                        }
//                    });
        builder.show();
    }
}