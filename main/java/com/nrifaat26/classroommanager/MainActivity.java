package com.nrifaat26.classroommanager;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView regText;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress=new ProgressDialog(this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please Wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);

        final EditText e_mailInput=(EditText)findViewById(R.id.e_mailInput);
        final EditText passwordInput=(EditText)findViewById(R.id.passwordInput);
        Button loginButton=(Button)findViewById(R.id.loginButton);
        regText= (TextView) findViewById(R.id.regText);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if (user!=null){
            finish();
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgress.show();
                validate(e_mailInput.getText().toString(),passwordInput.getText().toString());

            }
        });

       /* FirebaseAuth user = firebaseAuth.getInstance();

*/

        regText.setOnClickListener(new View.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegActivity.class));

            }
        });




    }
    private void validate(String usermail, String userPassword){

        firebaseAuth.signInWithEmailAndPassword(usermail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgress.dismiss();
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else{
                    mProgress.dismiss();
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}

