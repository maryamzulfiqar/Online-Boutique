package com.boutiquemanagement.boutiqueapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.util.Log;
import android.net.Uri;

public class place extends Activity {

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_place);
        StringBuilder sb = new StringBuilder();

        for (Object obj : cart.ar) {
            sb.append(obj.toString());
            sb.append("\t");
        }


        String finalString = sb.toString();
        sindh.ar.clear ();
        sindh.bill=0;
        cart.ar.clear ();
        Log.i("Send email", "");
        String[] TO = {MainActivity.mal};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summery");
        emailIntent.putExtra(Intent.EXTRA_TEXT, finalString +"   \n" +"Total Bill:"+cart.mbill);
cart.mbill=0;
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext (), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
