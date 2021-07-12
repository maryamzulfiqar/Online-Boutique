package com.boutiquemanagement.boutiqueapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.net.Uri;

public class checkOut extends Activity {
TextView tv;
EditText e;
ListView lv;
    String status="Pending";
DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_check_out);
    tv=(TextView) findViewById (R.id.textView13);
e=(EditText) findViewById (R.id.editText);
e.setHint ("Complete Address");
db=new DatabaseHelper (this);

        lv=(ListView) findViewById (R.id.lv);
        final ArrayAdapter<String> adopter=new ArrayAdapter<String> (checkOut.this,android.R.layout.simple_list_item_activated_1, cart.ar);
        lv.setAdapter(adopter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

tv.setText (cart.mbill.toString ()+ MainActivity.mid);

    }
@SuppressLint("LongLogTag")
public  void checko(View view){
    StringBuilder sb = new StringBuilder();

    for (Object obj : cart.ar) {
        sb.append(obj.toString());
        sb.append("\t");
    }

    String finalString = sb.toString();
    Integer id=MainActivity.mid;
    Integer bill=cart.mbill;

    db.addorder (id, bill, finalString, e.getText().toString (), status);

Toast.makeText (getApplicationContext (),"Order placed" ,Toast.LENGTH_SHORT).show ();


Intent intent2=new Intent (checkOut.this,place.class);
   startActivity (intent2);

}

}
