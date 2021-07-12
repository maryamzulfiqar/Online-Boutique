package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends Activity {

    DatabaseHelper db;
    EditText first_name, last_name, email, password, phone_number, renter;
    TextView existing;
    String f_name, l_name,mail, phone, pass,enter;
    private Object View;
    String epattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phoneptrn="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        first_name = (EditText) findViewById(R.id.first_name);
        last_name = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        renter = (EditText) findViewById(R.id.renter);
        phone_number = (EditText) findViewById(R.id.phone_number);
        existing = (TextView) findViewById(R.id.alreadyExist);

    }

    public void register(View v){

        mail = email.getText().toString();
        DatabaseHelper db = new DatabaseHelper(this);
        String checkname = db.checkusername(mail);
        if(mail.equals(checkname)) {
            Toast.makeText(this, "User Already Exist", Toast.LENGTH_SHORT).show();
            existing.setVisibility(v.VISIBLE);

        }
        else{
            if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() || first_name.getText().toString().isEmpty()||last_name.getText().toString().isEmpty()||phone_number.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(),"Please Fill the form completely",Toast.LENGTH_SHORT).show();

            }
            else {

                if (email.getText().toString().trim().matches(epattern)) {
                    if (password.getText().toString().length() > 8) {
                        if (password.getText().toString().equals(renter.getText().toString())) {

                            if ((phone_number.getText().toString()).matches(phoneptrn)) {
                                db = new DatabaseHelper(this);
                                f_name = first_name.getText().toString();
                                l_name = last_name.getText().toString();
                                mail = email.getText().toString();
                                pass = password.getText().toString();
                                phone = phone_number.getText().toString();
                                db.userRegister(f_name, l_name, mail, pass, phone);
                                Toast.makeText(this, "Register Successfully", Toast.LENGTH_LONG).show();

                                intent = new Intent(SignUp.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                phone_number.setError("Invalid phone Number");
                            }


                        } else {
                            renter.setError("Password not match");

                        }
                    } else {
                        password.setError("Length of password should be greater than 8");

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Email Address", Toast.LENGTH_SHORT).show();
                    email.setError("Invalid email address");

                }
            }
        }
    }
    public void goToLogin(View view){
        Intent intent = new Intent(SignUp.this, MainActivity.class);
        startActivity(intent);
        finishActivity(0);
    }
}
