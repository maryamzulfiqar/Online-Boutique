package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class DashBoard extends AppCompatActivity {
    public static    ArrayList<String> ar = new ArrayList<String>();
    VarietyListAdopter adapter = null;
    DatabaseHelper db;
    String[] namee;
    int bill=0,pq=0;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
 }
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
      return true;

  }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
      }
    }
    public  void category(View view){
        Toast.makeText(getApplicationContext(), "Category", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(DashBoard.this,Categories.class);
        startActivity(intent);
    }
    public  void cart(View view){
    // sindh.ar.add("Total Bill:   " +bill);
        Toast.makeText(getApplicationContext(), "Cart", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent (DashBoard.this,cart.class);
        intent.putExtra ("bill",sindh.bill);
        intent.putStringArrayListExtra("ar", sindh.ar);
        startActivity (intent);

    }
    public  void feedback(View view){
        Toast.makeText(getApplicationContext(), "Feedback", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(DashBoard.this,Feedback.class);
        startActivity (intent);
    }
    public  void contact(View view){
        Toast.makeText(getApplicationContext(), "Contact", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(DashBoard.this,Contacts.class);
        startActivity (intent);
    }
    public  void about(View view){
        Toast.makeText(getApplicationContext(), "About", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(DashBoard.this,about.class);
        startActivity (intent);
    }
    public  void history(View view) {
        Toast.makeText(getApplicationContext(), "History", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(DashBoard.this,OrderActivity.class);
        startActivity (intent);
    }

}