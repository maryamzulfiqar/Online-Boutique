package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contacts extends Activity {
    DatabaseHelper db;
    EditText name;
    EditText Eid;
    EditText phone;
    EditText msg;
    String email1;
    String epattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phoneptrn="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Eid = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        msg = (EditText)findViewById(R.id.msg);
        Button btn =  (Button) findViewById(R.id.button);
        name = (EditText)findViewById(R.id.name);
        db = new DatabaseHelper(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String toS = Eid.getText().toString();
                String sendTo = "fym@gmail.com";
                String email = Eid.getText().toString();
                String name1 = name.getText().toString();
                String phone1 = phone.getText().toString();
                String Mmsg = msg.getText().toString();
                if (email.matches(epattern)) {


                    if (( phone.getText().toString() ).matches(phoneptrn)) {
                        db.contact1(name1, email, Mmsg, phone1);
                        Intent email1 = new Intent(Intent.ACTION_SEND);
                        email1.putExtra(Intent.EXTRA_EMAIL, new String[]{sendTo});
                        //email1.putExtra(Intent.EXTRA_SUBJECT,);
                        email1.putExtra(Intent.EXTRA_TEXT, Mmsg);
                        email1.setType("message/rfc822");
                        startActivity(Intent.createChooser(email1, "Choose an app to send email"));
                        Toast.makeText(getApplicationContext(), "Thanks for contacting us. We'll get back to you as soon as possible!!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), "Your Form is not correctly filled ", Toast.LENGTH_SHORT).show();
                }
               else
                    Toast.makeText(getApplicationContext(), "Your Form is not correctly filled ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
