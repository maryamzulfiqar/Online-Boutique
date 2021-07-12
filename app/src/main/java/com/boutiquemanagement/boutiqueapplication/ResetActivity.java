package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResetActivity extends Activity {

    DatabaseHelper db;
    EditText email, password, repassword;
    String e_mail, pass, rePass;
    CheckBox checkBox;
    private Object View;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.rePass);
        checkBox = (CheckBox)findViewById(R.id.checkBox2);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    checkBox.setText("Hide Password");
                } else {
                    // hide password
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    checkBox.setText("Show Password");
                }
            }
        });

    }
    public void resetPassword(View view) {
        e_mail = email.getText().toString();
        pass = password.getText().toString();
        rePass = repassword.getText().toString();
        db = new DatabaseHelper(this);
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || repassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Fill the form completely", Toast.LENGTH_SHORT).show();
        } else {
            if (password.getText().toString().length() > 8) {
                if (password.getText().toString().equals(repassword.getText().toString())) {
                    db.resetPass(e_mail, pass);
                    Toast.makeText(this, "Password Reset Successfully", Toast.LENGTH_LONG).show();
                    intent = new Intent(ResetActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    repassword.setError("Password not match");
                }
            } else {
                password.setError("Length of password should be greater than 8");
            }
        }
    }
}
