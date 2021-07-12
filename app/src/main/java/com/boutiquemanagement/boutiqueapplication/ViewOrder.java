package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewOrder extends AppCompatActivity {

    public static ArrayList<String> myList = new ArrayList<String>();
    DatabaseHelper db;
    int id, price, mid;
    String description, address, status;
    TextView item_mid, item_price, item_desc, cust_address;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        db = new DatabaseHelper(this);
        Cursor cursor = db.getData("SELECT * FROM orders;");
        myList.clear();
        while(cursor.moveToNext()){
            id = cursor.getInt(0);
            mid = cursor.getInt(1);
            price = cursor.getInt(2);
            description = cursor.getString(3);
            address = cursor.getString(4);
            status = cursor.getString(5);
            myList.add(id+" \t\t"+description+" \t\tRs:"+price+" \t\t"+status);
        }
        listView= (ListView) findViewById(R.id.list_item);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String> (ViewOrder.this,android.R.layout.simple_list_item_activated_1, myList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewOrder.this);

                CharSequence[] item = {"Open"};
                dialog.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = db.getData("SELECT oid FROM orders;");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogOpen(ViewOrder.this, arrID.get(position));
                            adapter.notifyDataSetChanged();
                        }

                        else {

                        } }
                });
                dialog.show();
                listView.setAdapter(adapter);
            }
        });

    }

    private void showDialogOpen(Activity activity, final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.order_update);
        dialog.setTitle("Open");
        final EditText current_status = (EditText) dialog.findViewById(R.id.curStatus);
        final EditText editStatus = (EditText) dialog.findViewById(R.id.updatedStatus);

        Button statusUpdate = (Button) dialog.findViewById(R.id.statusUpdate);

        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        current_status.setText(status);
        statusUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.updateOStatus(
                            editStatus.getText().toString(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateOrderList ();
            }
        });
    }
    private void updateOrderList(){
        // get all data from sqlite
        Cursor cursor = db.getData ("SELECT * FROM orders;");
        myList.clear();
        while (cursor.moveToNext ()) {
            id = cursor.getInt(0);
            mid = cursor.getInt(1);
            price = cursor.getInt(2);
            description = cursor.getString(3);
            address = cursor.getString(4);
            status = cursor.getString(5);

            myList.add(id+" \t\t"+description+" \t\tRs:"+price+" \t\t"+status);
        }
        
    }

}