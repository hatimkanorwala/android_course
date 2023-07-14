package com.app.hatim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hatim.db.subject_db;

import java.util.ArrayList;
import java.util.Arrays;

public class userList extends AppCompatActivity {

    ListView _listView;
    TextView _output;

    Button _user_btnPopup;
    String tutorial[] = new String[]{"C","AM","Digital Electronics","Communication Skills","Operating System","C++","WT"};
    ArrayAdapter<String> arr;
    ArrayList<String> arrayList = new ArrayList<String>();

    subject_db db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        _listView = findViewById(R.id.user_listView);
        _output = findViewById(R.id.user_selectedtItem);
        _user_btnPopup = findViewById(R.id.user_btnPopup);

        displayOutput();

        db = new subject_db(userList.this);


        _listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String value = arr.getItem(position);
                Toast.makeText(userList.this, "Item Clicked " + value, Toast.LENGTH_SHORT).show();
                _output.setText("You have selected: " + value);

                AlertDialog.Builder builder = new AlertDialog.Builder(userList.this);
                builder.setTitle("Delete Item");
                builder.setMessage("You do want to delete " + value);
                AlertDialog alertDialog = builder.create();
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteRecord(value);
                        arrayList.clear();
                        displayDBOutput();
                        alertDialog.dismiss();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        _user_btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(userList.this);
                builder.setCancelable(false);
                LayoutInflater inflater = getLayoutInflater();
                View view1 = inflater.inflate(R.layout.add_subject,null);
                TextView _btnClose = view1.findViewById(R.id.btnClose);
                EditText _subject = view1.findViewById(R.id.add_subjectName);
                Button _btnAdd = view1.findViewById(R.id.add_subjectBtn);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();
                _btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                _btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String subject =_subject.getText().toString().trim();
                        if(subject.length() > 0)
                        {
                            Boolean result = db.insertSubject(subject);
                            if(result == true)
                            {
                                _listView.setAdapter(null);
                                arrayList.clear();
                                displayDBOutput();
                                dialog.dismiss();
                            }
                            else
                            {
                                Toast.makeText(userList.this, "Unable to add new Subject, Try Again!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            _subject.requestFocus();
                            _subject.setError("Please enter Subject");
                        }
                    }
                });
            }
        });




    }

    private void displayOutput() {
        arr = new ArrayAdapter<>(userList.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,tutorial);
        _listView.setAdapter(arr);
    }
    private void displayDBOutput()
    {
        Cursor res = db.readData();

        if(res.getCount() == 0)
        {
            Toast.makeText(this, "No Data Found in DB", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(res.moveToNext())
            {
                arrayList.add(res.getString(0));
            }
            arr = new ArrayAdapter<>(userList.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList);
            _listView.setAdapter(arr);

        }

    }
}