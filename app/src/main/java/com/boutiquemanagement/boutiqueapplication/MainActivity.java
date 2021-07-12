package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
public static  Integer mid=0;
public static String mal="";
    Button login, signup;
    TextView forgot;
    EditText email, pwd;
    CheckBox checkBox;
    String username, pwd1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signUp);
        forgot = (TextView) findViewById(R.id.forgot);
        email = (EditText) findViewById(R.id.email);
        pwd = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // show password
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                    checkBox.setText("Hide Password");
                } else {
                    // hide password
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                    checkBox.setText("Show Password");
                }
            }
        });

    }
    public void login(View v)
    {
        username = email.getText().toString();
        pwd1 = pwd.getText().toString();
        DatabaseHelper db = new DatabaseHelper(this);

  //  String checkpassword = db.checkuserpassword(pwd1);
//     String checkname = db.checkusername(username);
        if(username.equals ("admin@gmail.com") && pwd1.equals ("admin123")){
            Intent intent=new Intent (MainActivity.this,admindBoard.class);
            startActivity (intent);
        } else  if(pwd1.equals(db.checkuserpassword(pwd1)) && username.equals(db.checkusername(username))) {
            String id = db.checkid (username);
            mid=Integer.parseInt (id);
            mal=username;
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            email.setText("");
            pwd.setText("");
            Intent intent=new Intent(MainActivity.this,DashBoard.class);
            startActivity(intent);
        } else{
         Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
         forgot.setVisibility(View.VISIBLE);
        }
    }


    public void signUp(View view)
    {
        Intent intent=new Intent(MainActivity.this,SignUp.class);
        startActivity(intent);

    }
    public void forgotPassword(View view){
        Intent intent = new Intent(MainActivity.this, ResetActivity.class);
        intent.putExtra("email", username);
        DatabaseHelper db = new DatabaseHelper(this);
        String checkname = db.checkusername(username);

        if(username.equals(checkname)) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            forgot.setVisibility(View.INVISIBLE);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
        }
    }
}
