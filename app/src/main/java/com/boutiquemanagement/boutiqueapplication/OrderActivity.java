package com.boutiquemanagement.boutiqueapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    public static ArrayList<String> myList = new ArrayList<String>();
    DatabaseHelper db;
    int id, price, mid;
    String description, address, status;
    TextView tv;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        db = new DatabaseHelper(this);
        tv = (TextView) findViewById(R.id.textView2);
        Cursor cursor = db.getData("SELECT * FROM orders WHERE mid="+MainActivity.mid+"");
        myList.clear();
        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            mid = cursor.getInt(1);
            price = cursor.getInt(2);
            description = cursor.getString(3);
            address = cursor.getString(4);
            status = cursor.getString(5);

            myList.add(id+" \t"+description+" \tRs:"+price+" \t"+status);
        }
        listView= (ListView) findViewById(R.id.list_item);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String> (OrderActivity.this,android.R.layout.simple_list_item_activated_1, myList);
        listView.setAdapter(adapter);
    }

    public void goBack(View view){
        Intent intent = new Intent(OrderActivity.this, DashBoard.class);
        startActivity(intent);
    }
}