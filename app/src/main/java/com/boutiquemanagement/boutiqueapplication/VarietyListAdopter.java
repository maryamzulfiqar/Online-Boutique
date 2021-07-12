package com.boutiquemanagement.boutiqueapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class VarietyListAdopter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Variety> VarietyList;

    public VarietyListAdopter(Context context, int layout, ArrayList<Variety> VarietyList) {
        this.context = context;
        this.layout = layout;
        this.VarietyList = VarietyList;
    }

    @Override
    public int getCount() {
        return VarietyList.size();
    }

    @Override
    public Object getItem(int position) {
        return VarietyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice,txtc,txts;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.txtc=(TextView) row.findViewById(R.id.txtcategory);
            holder.txts=(TextView) row.findViewById(R.id.txtsubcategory);
            holder.imageView = (ImageView) row.findViewById(R.id.imgFood);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Variety variety = VarietyList.get(position);
        String p=variety.getPrice ().toString ();
        holder.txtName.setText(variety.getName());
        holder.txtPrice.setText (p);
        holder.txts.setText(variety.getSubcategory());
        holder.txtc.setText(variety.getCategory());

        byte[] foodImage = variety.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}

