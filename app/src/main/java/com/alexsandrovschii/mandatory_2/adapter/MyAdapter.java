package com.alexsandrovschii.mandatory_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexsandrovschii.mandatory_2.R;
import com.alexsandrovschii.mandatory_2.model.Snap;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    // adapter factory for producing rows based on some data source

    private List<Snap> items;
    private LayoutInflater layoutInflater; // takes an XML file and make into a Java object

    public MyAdapter(List<Snap> items, Context context) {
        this.items = items;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
           convertView = layoutInflater.inflate(R.layout.myrow, null);
        }
        LinearLayout linearLayout = (LinearLayout)convertView;
        TextView textViewUser = convertView.findViewById(R.id.textViewUserMyRow);
//        ImageView imageView = convertView.findViewById(R.id.imageViewMyRow);
        TextView textViewDate = convertView.findViewById(R.id.textViewDateMyRow);
        if(textViewUser != null){
            textViewUser.setText(items.get(position).getUser() + " added a new Snap");
        }
//        if(imageView != null){
//            imageView.setImageBitmap(items.get(position).getImage());
//        }
        if(textViewDate != null){
            textViewDate.setText(items.get(position).compareDates() + " hours left to watch");
        }


        return linearLayout;
    }
}
