package com.boutiquemanagement.boutiqueapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;


public class AdminAdd extends Activity {

    private static final int PICK_IMAGE = 100;
    RadioGroup rg1, rg2;
    RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8;
    EditText e1, e2;
    ImageView prodImage;
    final int REQUEST_CODE_GALLERY = 999;
    public static DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_admin_add);
        db = new DatabaseHelper (this);
        prodImage = (ImageView) findViewById (R.id.imageView);
        String province1 = "punjab";
        String province2 = "Sindh";
        String province3 = "Balochistan";
        String province4 = "KPK";
        String province5 = "Gilgit Baltistan";


        String checkpassword = db.checkprovince (province1);
        if (!province1.equals (checkpassword)) {
            db.addCategory ("Punjab");
        }
        checkpassword = db.checkprovince (province2);

        if (!province2.equals (checkpassword)) {
            db.addCategory ("Sindh");
        }
        checkpassword = db.checkprovince (province3);

        if (!province3.equals (checkpassword)) {
            db.addCategory ("Balochistan");
        }
        checkpassword = db.checkprovince (province4);

        if (!province4.equals (checkpassword)) {
            db.addCategory ("KPK");
        }
        checkpassword = db.checkprovince (province5);

        if (!province5.equals (checkpassword)) {
            db.addCategory ("Gilgit Baltistan");
        }


    }

    public void uploadImage(View view) {


        Intent gallery = new Intent (Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult (gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult (requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData ();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver ().openInputStream (imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace ();
            }

            Bitmap bitmap = BitmapFactory.decodeStream (inputStream);
            prodImage.setImageBitmap (bitmap);
        }
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable ()).getBitmap ();
        ByteArrayOutputStream stream = new ByteArrayOutputStream ();
        bitmap.compress (Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray ();
        return byteArray;
    }

    public void sstore(View view) {
        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.price);
        rg1 = (RadioGroup) findViewById(R.id.rg1);
        rg2 = (RadioGroup) findViewById(R.id.rg2);
        String name = e1.getText().toString();
        Integer price = Integer.parseInt(e2.getText().toString());
        String cate = "";
        String subcat = "";
        int selected = rg1.getCheckedRadioButtonId();
        RadioButton rb1 = (RadioButton) findViewById(selected);
        int selectedtype = rg2.getCheckedRadioButtonId();
        RadioButton rb2 = (RadioButton) findViewById(selectedtype);

        if (rb1.getText().toString().equals("Sindh")) {
            cate = rb1.getText().toString();
            subcat = rb2.getText().toString();
            db.addsindh(name, price, subcat, cate, imageViewToByte(prodImage));
            Toast.makeText(getApplicationContext(), "Added successfully!!!",Toast.LENGTH_SHORT).show();

        }
        else if (rb1.getText().toString().equals("Punjab")) {
            cate = rb1.getText ().toString ();
            subcat = rb2.getText ().toString ();
            db.addpunjab (name, price, subcat, cate, imageViewToByte (prodImage));
            Toast.makeText(getApplicationContext(), "Added successfully!!!",Toast.LENGTH_SHORT).show();
        }
        else if (rb1.getText().toString().equals("Balochistan")) {
            cate = rb1.getText ().toString ();
            subcat = rb2.getText ().toString ();
            db.addbalouchistan (name, price, subcat, cate, imageViewToByte (prodImage));
            Toast.makeText(getApplicationContext(), "Added successfully!!!",Toast.LENGTH_SHORT).show();
        } else if (rb1.getText().toString().equals("KPK")) {
            cate = rb1.getText ().toString ();
            subcat = rb2.getText ().toString ();
            db.addKPK (name, price, subcat, cate, imageViewToByte (prodImage));
            Toast.makeText(getApplicationContext(), "Added successfully!!!",Toast.LENGTH_SHORT).show();
        } else if (rb1.getText().toString().equals("Gilgit Baltistan")) {
            cate = rb1.getText ().toString ();
            subcat = rb2.getText ().toString ();
            db.addGilgit (name, price, subcat, cate, imageViewToByte (prodImage));
            Toast.makeText(getApplicationContext(), "Added successfully!!!",Toast.LENGTH_SHORT).show();
//            Toast.makeText (getApplicationContext (),"Please select the right category", Toast.LENGTH_SHORT).show ();
        } else{
            Toast.makeText (getApplicationContext (),"Please select the right category", Toast.LENGTH_SHORT).show ();
        }
    }
}
