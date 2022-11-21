package com.nrifaat26.classroommanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DBActivity extends AppCompatActivity {


    EditText Room , Period;
    EditText Allocation;
    TextView textView,textView2;
    DatabaseHelper myDB;
    Button btnAdd,btnView;
     String variable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        Room=(EditText)findViewById(R.id.Room);
        Period=(EditText)findViewById(R.id.Period);
        Allocation=(EditText)findViewById(R.id.Allocation);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=(Button)findViewById(R.id.btnView);
        myDB=new DatabaseHelper(this);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variable=Period.getText().toString();
               Intent intent= new Intent(DBActivity.this,ViewListContents.class);
                intent.putExtra("per", variable);
                startActivity(intent);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String room= Room.getText().toString();
                String period= Period.getText().toString();
                String alloc=Allocation.getText().toString();

                if (room.length()!=0&&period.length()!=0&&alloc.length()!=0){
                    AddData(period,room,alloc);
                    Room.setText("");
                    Period.setText("");
                    Allocation.setText("");

                }

                else{
                    Toast.makeText(DBActivity.this,"Please Enter All fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
public void AddData(String Period, String Room, String Allocation){

        boolean insertData= myDB.addData(Period,Room,Allocation);
    if (insertData==true){
        Toast.makeText(DBActivity.this,"Successful Allocation",Toast.LENGTH_SHORT).show();
    }
    else{
        Toast.makeText(DBActivity.this,"Allocation Failed",Toast.LENGTH_SHORT).show();
    }
}}//appcomp

