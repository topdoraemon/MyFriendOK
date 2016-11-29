package com.aoyama.atc.myfriend;

/**
 * Created by somsak on 29/11/2559.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



public class MyAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] nameStrings, iconStrings;
    private TextView textView;
    private ImageView imageView;

    public MyAdapter(Context context, String[] nameStrings, String[] iconStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.iconStrings = iconStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_layout, viewGroup, false);

        textView = (TextView) view1.findViewById(R.id.textView2);
        imageView = (ImageView) view1.findViewById(R.id.imageView3);

        textView.setText(nameStrings[i]);
        Picasso.with(context).load(iconStrings[i]).into(imageView);


        return view1;
    }
} // Main Class