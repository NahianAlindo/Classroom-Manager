package com.nrifaat26.classroommanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;

import com.nrifaat26.classroommanager.R;
import com.nrifaat26.classroommanager.helper.InputValidation;
import com.nrifaat26.classroommanager.model.Beneficiary;

import com.nrifaat26.classroommanager.sql.DatabaseHelper;

public class Main2Activity extends AppCompatActivity  {

    private final AppCompatActivity activity = Main2Activity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutBeneficiaryName;
    private TextInputLayout textInputLayoutBeneficiaryEmail;
    private TextInputLayout textInputLayoutBeneficiaryAddress;
    private TextInputLayout textInputLayoutBeneficiaryCountry;
    private TextInputLayout textInputLayoutBeneficiaryId;

    private TextInputEditText textInputEditTextBeneficiaryName;
    private TextInputEditText textInputEditTextBeneficiaryEmail;
    private TextInputEditText textInputEditTextBeneficiaryAddress;
    private TextInputEditText textInputEditTextBeneficiaryCountry;
    private TextInputEditText textInputEditTextBeneficiaryId;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewBenefList;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Beneficiary beneficiary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initViews();
        initObjects();
        //initListeners();

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutBeneficiaryName = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryName);
        textInputLayoutBeneficiaryEmail = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryEmail);
        textInputLayoutBeneficiaryAddress = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryAddress);
        textInputLayoutBeneficiaryCountry = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryCountry);
        textInputLayoutBeneficiaryId = (TextInputLayout) findViewById(R.id.textInputLayoutBeneficiaryId);

        textInputEditTextBeneficiaryName = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryName);
        textInputEditTextBeneficiaryEmail = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryEmail);
        textInputEditTextBeneficiaryAddress = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryAddress);
        textInputEditTextBeneficiaryCountry = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryCountry);
        textInputEditTextBeneficiaryId = (TextInputEditText) findViewById(R.id.textInputEditTextBeneficiaryId);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewBenefList = (AppCompatTextView) findViewById(R.id.appCompatTextViewBenefList);

        appCompatButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDataToSQLite();
            }
        });

        appCompatTextViewBenefList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsIntent = new Intent(activity, BeneficiaryListActivity.class);
                accountsIntent.putExtra("ID", textInputEditTextBeneficiaryId.getText().toString().trim());
                accountsIntent.putExtra("NAME", textInputEditTextBeneficiaryName.getText().toString().trim());
                accountsIntent.putExtra("EMAIL", textInputEditTextBeneficiaryEmail.getText().toString().trim());
                accountsIntent.putExtra("ADDRESS", textInputEditTextBeneficiaryAddress.getText().toString().trim());
                accountsIntent.putExtra("COUNTRY", textInputEditTextBeneficiaryCountry.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);
            }
        });
    }



    /**
     * This method is to initialize listeners
     */


    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        beneficiary = new Beneficiary();
    }




    private void postDataToSQLite() {


        if (!databaseHelper.checkUser(textInputEditTextBeneficiaryEmail.getText().toString().trim())) {

            beneficiary.setId(Integer.parseInt(textInputEditTextBeneficiaryId.getText().toString().trim()));
            beneficiary.setName(textInputEditTextBeneficiaryName.getText().toString().trim());
            beneficiary.setEmail(textInputEditTextBeneficiaryEmail.getText().toString().trim());
            beneficiary.setAddress(textInputEditTextBeneficiaryAddress.getText().toString().trim());
            beneficiary.setCountry(textInputEditTextBeneficiaryCountry.getText().toString().trim());


            databaseHelper.addBeneficiary(beneficiary);

            // Snack Bar to show success message that record saved successfully
            Intent accountsIntent = new Intent(activity, BeneficiaryListActivity.class);
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT)
                    .show();
            accountsIntent.putExtra("ID", textInputEditTextBeneficiaryId.getText().toString().trim());
            accountsIntent.putExtra("NAME", textInputEditTextBeneficiaryName.getText().toString().trim());
            accountsIntent.putExtra("EMAIL", textInputEditTextBeneficiaryEmail.getText().toString().trim());
            accountsIntent.putExtra("ADDRESS", textInputEditTextBeneficiaryAddress.getText().toString().trim());
            accountsIntent.putExtra("COUNTRY", textInputEditTextBeneficiaryCountry.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountsIntent);


        }


    }

    private void emptyInputEditText() {
        textInputEditTextBeneficiaryName.setText(null);
        textInputEditTextBeneficiaryEmail.setText(null);
        textInputEditTextBeneficiaryAddress.setText(null);
        textInputEditTextBeneficiaryCountry.setText(null);
    }
}