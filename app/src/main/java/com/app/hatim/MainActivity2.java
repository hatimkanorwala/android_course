package com.app.hatim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    TextView _selectedItem;
    Button _openDialogButton,_btnOpenMultipleDialog;

    final int[] checedItem = {-1};
    boolean[] checkedItems;
    List<String> selectedItems;
    String data = "";

    final String[] listItems = new String[]{"Android","IOS","Windows","Blackberry"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        _selectedItem = findViewById(R.id.selectedText);
        _openDialogButton = findViewById(R.id.btnOpenDialog);
        _btnOpenMultipleDialog = findViewById(R.id.btnOpenMultipleDialog);

        checkedItems = new boolean[listItems.length];
       selectedItems = Arrays.asList(listItems);

        _openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //To Select Single Items from Alert Dialog and display the result
                 singleDialogSelector();
            }
        });
        _btnOpenMultipleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To Single Multiple Items from Alert Dialog and display the result
                multipleDialogSelector();
            }
        });
    }

    private void multipleDialogSelector() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Select Multiple Items");

        builder.setMultiChoiceItems(listItems,checkedItems,(dialog,which,isChecked) -> {
            checkedItems[which]= isChecked;
            String currentItem = selectedItems.get(which);
        });
        builder.setPositiveButton("Submit",(dialog,which) -> {
            data = "";
           for(int i=0;i<checkedItems.length;i++)
           {
               if(checkedItems[i])
               {
                data += " ," + selectedItems.get(i);
               }
           }
            _selectedItem.setText("Selected Items: " + data);
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void singleDialogSelector() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        builder.setTitle("Select an Item");
        builder.setSingleChoiceItems(listItems, checedItem[0], (dialog,which) -> {
            checedItem[0] = which;
            _selectedItem.setText("Selected Item: " + listItems[which]);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}