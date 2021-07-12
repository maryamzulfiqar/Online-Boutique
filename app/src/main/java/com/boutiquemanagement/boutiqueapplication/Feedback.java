package com.boutiquemanagement.boutiqueapplication;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Feedback extends Activity {
    EditText msg, name, email;
    RadioGroup rg;
    RadioButton ques, sugg, comm;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        msg = (EditText) findViewById(R.id.msg);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

        db = new DatabaseHelper(this);

    }

    public void feed(View view) {

        String msg1, name1, ques1, sugg1, comm1, email1;
        msg1 = msg.getText().toString();
        name1 = name.getText().toString();
        email1 = email.getText().toString();
        Button btn = (Button) findViewById(R.id.feed);
        // int selected = rg.getCheckedRadioButtonId();
        ques = (RadioButton) findViewById(R.id.quest);
        sugg = (RadioButton) findViewById(R.id.sugg);
        comm = (RadioButton) findViewById(R.id.comm);

        if(name1.isEmpty()|| email1.isEmpty()|| msg1.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Fill the Form Correctly.", Toast.LENGTH_SHORT).show();
        }
        else {
            if (ques.isChecked()) {
                ques1 = ques.getText().toString();
                db.feedbackRegister(name1, email1, msg1, ques1);
            } else if (sugg.isChecked()) {
                sugg1 = sugg.getText().toString();
                db.feedbackRegister(name1, email1, msg1, sugg1);
            }
            if (comm.isChecked()) {
                comm1 = ques.getText().toString();
                db.feedbackRegister(name1, email1, msg1, comm1);
            }
            Toast.makeText(getApplicationContext(), "Your Response has been Recorderd Thanks for your feedback .", Toast.LENGTH_SHORT).show();
        }

    }
}