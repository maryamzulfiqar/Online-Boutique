package com.boutiquemanagement.boutiqueapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class AdminDelKPK extends AppCompatActivity {

    GridView gridView;
    ArrayList<Variety> list;
    VarietyListAdopter adapter = null;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.variety_list_activity);
        db=new DatabaseHelper (this);
        gridView = (GridView) findViewById (R.id.gridView);
        list = new ArrayList<> ();
        adapter = new VarietyListAdopter (this, R.layout.variety_items, list);
        gridView.setAdapter (adapter);
        // get all data from sqlite
        Cursor cursor = db.getData ("SELECT * FROM kpk;");
        list.clear ();
        while (cursor.moveToNext ()) {
            Integer id = cursor.getInt (0);
            String name = cursor.getString (1);
            Integer price = cursor.getInt (2);
            String cat = cursor.getString (3);
            String subcat = cursor.getString (4);
            byte[] image = cursor.getBlob (5);
            list.add (new Variety (name, price, cat, subcat, image, id));
        }
        adapter.notifyDataSetChanged ();
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                CharSequence[] items = { "Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(AdminDelKPK.this);
                dialog.setTitle("Please Select Any One");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = AdminAdd.db.getData("SELECT kid FROM kpk;");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){ arrID.add(c.getInt(0));
                            }
                            showDialogUpdate(AdminDelKPK.this, arrID.get(position));
                        }
                        else {
                            Cursor c = AdminAdd.db.getData("SELECT kid FROM kpk;");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }

                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_variety_activity);
        dialog.setTitle("Update");
        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        final EditText edtCategory = (EditText) dialog.findViewById(R.id.edtcat);
        final EditText edtSubCategory = (EditText) dialog.findViewById(R.id.edtsub);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        AdminDelKPK.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AdminAdd.db.updateDataKPK(
                            edtName.getText().toString(),
                            Integer.parseInt( edtPrice.getText().toString()),
                            edtSubCategory.getText().toString(),
                            edtCategory.getText().toString(),
                            AdminAdd.imageViewToByte(imageViewFood),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Updated successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateVarietyList ();
            }
        });
    }

    private void showDialogDelete(final int idFood){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(AdminDelKPK.this);
        dialogDelete.setTitle("Alert!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AdminAdd.db.deleteDataKPK(idFood);
                    Toast.makeText(getApplicationContext(), "Deleted successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateVarietyList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateVarietyList(){
        // get all data from sqlite
        Cursor cursor = AdminAdd.db.getData ("SELECT * FROM kpk;");
        list.clear ();
        while (cursor.moveToNext ()) {
            Integer id = cursor.getInt (0);
            String name = cursor.getString (1);
            Integer price = cursor.getInt (2);
            String cat = cursor.getString (3);
            String subcat = cursor.getString (4);

            byte[] image = cursor.getBlob (5);

            list.add (new Variety (name, price, cat, subcat, image, id));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewFood.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}