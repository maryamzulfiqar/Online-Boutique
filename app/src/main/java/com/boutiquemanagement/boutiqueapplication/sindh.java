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
public class sindh extends AppCompatActivity {
    public static    ArrayList<String> ar = new ArrayList<String>();

    // public static String finalName="my";
    GridView gridView;
    ArrayList<Variety> list;
    VarietyListAdopter adapter = null;
    DatabaseHelper db;
    String[] namee;
   public static int bill=0;
    int i=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.variety_list_activity);
        gridView = (GridView) findViewById (R.id.gridView);
        db=new DatabaseHelper (this);

        list = new ArrayList<> ();
        adapter = new VarietyListAdopter (this, R.layout.variety_items, list);
        gridView.setAdapter (adapter);
        // get all data from sqlite
        Cursor cursor1 = db.getData ("SELECT * FROM sindh;");
        list.clear ();
        while (cursor1.moveToNext ()) {
            Integer id = cursor1.getInt (0);
            String name = cursor1.getString (1);
            Integer price = cursor1.getInt (2);
            String cat = cursor1.getString (3);
            String subcat = cursor1.getString (4);
            byte[] image = cursor1.getBlob (5);
            list.add (new Variety (name, price, cat, subcat, image, id));
        }
        adapter.notifyDataSetChanged ();
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {

                CharSequence[] items = {"Open"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(sindh.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = db.getData("SELECT sid FROM sindh;");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogopen (sindh.this, arrID.get(position));
                        }

                        else {

                        } }
                });
                dialog.show();
                return true;
            }});}
    ImageView imageViewFood;
    private void showDialogopen(Activity activity, final int position){
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_variety_activity);
        dialog.setTitle("Open");
        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        final EditText edtCategory = (EditText) dialog.findViewById(R.id.edtcat);
        final EditText edtSubCategory = (EditText) dialog.findViewById(R.id.edtsub);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
        btnUpdate.setText ("Add to Cart");
edtCategory.setHint ("Quantity");
        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.95);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        Cursor cursor = db.getData ("Select * from sindh where sid=" + position + "");
        Integer id = 0,price=0;
        String name="",cat="",subcat="";
        byte[] image=null;
        while (cursor.moveToNext ()) {
            id = cursor.getInt (0);
            name = cursor.getString (1);
            price = cursor.getInt (2);
            cat = cursor.getString (3);
            subcat = cursor.getString (4);

            image= cursor.getBlob (5);
        }

        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageViewFood.setImageBitmap(bitmap);

        edtName.setText(name);
        edtPrice.setText(price.toString ());
        //    edtCategory.setText("");
        edtSubCategory.setText(subcat);
        String pr=price.toString ();
//Integer purchase=Integer.parseInt (edtCategory.toString ());
//Integer p=Integer.parseInt (edtCategory.getText ().toString ());
        final String finalName = name;
        final String finalSubcat = subcat;
        final String finalCat = cat;
        final Integer finalPrice = price;
        final Integer finalId = id;
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bill=bill+(finalPrice*Integer.parseInt (edtCategory.getText ().toString ()));
                //   finalName = edtName.getText ().toString ();
               ar.add(finalId +"  ,"+ finalName + "  ," + finalCat + "   ," + finalSubcat + "  ,"+ finalPrice +"  ,"+ (finalPrice*(Integer.parseInt (edtCategory.getText ().toString ()))));
                dialog.dismiss();
                //  Toast.makeText(getApplicationContext(), "Added successfully!!!" + finalName ,Toast.LENGTH_SHORT).show();
                //       Intent intent=new Intent (sindh.this,cart.class);
                //      intent.putExtra ("finalName","HELLO");
                //   startActivity (intent);

            }
        });
    }

    public void cartc(View view)
    {
        Intent intent=new Intent (sindh.this,cart.class);
intent.putExtra ("bill", bill);
        intent.putStringArrayListExtra("ar",ar);
        startActivity(intent);
    }
}
