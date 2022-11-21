package com.nrifaat26.classroommanager;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegActivity extends AppCompatActivity {


    private EditText RegName,EmailReg,RegPass;
    private Button regButton;
    private TextView AlreadySigned;
   private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        RegName= (EditText)findViewById(R.id.RegName);
        EmailReg=(EditText)findViewById(R.id.EmailReg);
        RegPass=(EditText)findViewById(R.id.RegPass);
        regButton=(Button)findViewById(R.id.regButton);
       firebaseAuth= FirebaseAuth.getInstance();
        //setting up interface for button click
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()){
                    String userEmail= EmailReg.getText().toString().trim();
                    String userPass= RegPass.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(userEmail,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegActivity.this,"Registration Successful", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
        AlreadySigned=(TextView)findViewById(R.id.AlreadySigned);
        //setting up interface for textview click
        AlreadySigned.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegActivity.this,MainActivity.class));
            }
        });

    }

    //validating if fields are filled or empty
    private boolean validate()
    {
        Boolean result= false;
        String name= RegName.getText().toString();
        String pass= RegPass.getText().toString();
        String mail= EmailReg.getText().toString();
        if(name.isEmpty()||pass.isEmpty()||mail.isEmpty()) {
        Toast.makeText(this,"Please Fill up all the fields",Toast.LENGTH_SHORT).show();
        }
        else{result=true;}
        return result;
    }
}
