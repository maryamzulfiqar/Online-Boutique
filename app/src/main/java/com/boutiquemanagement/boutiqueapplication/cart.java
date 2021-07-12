package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class cart extends Activity {
    TextView tv;
    public static Integer mbill;
    ListView lv;
    Button checkBtn;
    public static ArrayList<String> ar = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_cart);
        Intent intent1=getIntent ();
        tv=(TextView) findViewById (R.id.textView13);
        checkBtn = (Button) findViewById(R.id.button5);
        ar = getIntent().getStringArrayListExtra("ar");
        final Integer[] bill = {getIntent ().getIntExtra
                ("bill",0)};
        tv.setText (bill[0].toString ());

        lv=(ListView) findViewById (R.id.lv);
        final  ArrayAdapter<String> adopter=new ArrayAdapter<String> (cart.this,android.R.layout.simple_list_item_activated_1, ar);
        lv.setAdapter(adopter);
        if(lv.getCount()==0){
            checkBtn.setEnabled(false);
        } else{
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=adopter.getItem (position);
                String[] valArray = value.split("\\,");
                Toast.makeText (getApplicationContext (),valArray[0],Toast.LENGTH_SHORT).show ();
                lv.setAdapter(adopter);
                  ar.remove(position);

                String val2=valArray[5];
                Integer bill2=Integer.parseInt (val2);
                bill[0] -= bill2;
                tv.setText (bill[0].toString ());

            }
        });
        checkBtn.setEnabled(true);
        }


    }
    public  void checkot(View view){
        Intent intent=new Intent (cart.this,checkOut.class);
        startActivity (intent);
        mbill=Integer.parseInt (tv.getText ().toString ());

    }
}