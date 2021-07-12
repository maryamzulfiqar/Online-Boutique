package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Categories extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

    }
    public void sclick(View view){
        Toast.makeText(getApplicationContext(), "Sindh", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent (Categories.this, sindh.class);
        startActivity (intent);


    }
    public void pclick(View view){
       Toast.makeText(getApplicationContext(),"Punjab",Toast.LENGTH_SHORT).show();
        Intent intent1=new Intent (Categories.this, Punjab.class);
        startActivity (intent1);

    }
    public void bclick(View view){
        Toast.makeText(getApplicationContext(),"Balouchistan",Toast.LENGTH_SHORT).show();
        Intent intent2=new Intent (Categories.this, Balouchistan.class);
        startActivity (intent2);

    }
    public void kclick(View view){
        Toast.makeText(getApplicationContext(),"KPK",Toast.LENGTH_SHORT).show();
        Intent intent3=new Intent (Categories.this, KhaiberPK.class);
        startActivity (intent3);
    }

    public void gclick(View view){
        Toast.makeText(getApplicationContext(),"Gilgit Baltistan",Toast.LENGTH_SHORT).show();
        Intent intent4=new Intent (Categories.this, GilgitBaltistan.class);
        startActivity (intent4);
    }
}
