package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;
public class admindBoard extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admind_board);
    }
    public void add(View view){
        Intent intent=new Intent(admindBoard.this,AdminAdd.class);
               startActivity (intent);
    }
    public void update(View view){
            CharSequence[] items = {"Sindh", "Punjab", "Balochistan","KPK","Gilgit Baltistan"};
            AlertDialog.Builder dialog = new AlertDialog.Builder (admindBoard.this);
            dialog.setTitle ("Choose an action");
            dialog.setItems (items, new DialogInterface.OnClickListener () {
                        @Override
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                Intent intent=new Intent (admindBoard.this,AdminDelUp.class);
                                startActivity (intent);
                            } else if (item == 1) {
                                Intent intent1=new Intent (admindBoard.this,AdminDelPunjab.class);
                                startActivity (intent1);
                            } else if(item==2){

                                Intent intent2=new Intent (admindBoard.this,AdminDelUpBal.class);
                                startActivity (intent2);
                            }
                            else if(item==3){
                                Intent intent3=new Intent (admindBoard.this,AdminDelKPK.class);
                                startActivity (intent3);
//                                Toast.makeText(getApplicationContext(), "Please Choose", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent intent4=new Intent (admindBoard.this,AdminDelGilgit.class);
                                startActivity (intent4);
//                                Toast.makeText(getApplicationContext(), "Please Choose", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
            );
            dialog.show ();
        }

    public void viewOrders(View view){
        Intent intent = new Intent(admindBoard.this, ViewOrder.class);
        startActivity(intent);
    }
    }
