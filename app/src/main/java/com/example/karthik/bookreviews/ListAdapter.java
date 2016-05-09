package com.example.karthik.bookreviews;

/**
 * Created by Karthik on 4/15/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Book> {

    private List<Book> items;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }
    public ListAdapter(Context context, int resource, List<Book> items) {
        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {

            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        Book p = getItem(position);

        if (p != null) {

            TextView im=(TextView)v.findViewById(R.id.icon);
            TextView tt1 = (TextView) v.findViewById(R.id.title);
            TextView tt3 = (TextView) v.findViewById(R.id.author);
            RatingBar rb = (RatingBar) v.findViewById(R.id.rating);

//            if (im != null) {
//
//
////                tt.setText("" + p.getId());
//                Log.i("Title of Book",p.getTitle());
//
//
//                if(position==0){
//                    im.setImageResource(R.drawable.icon1);
//
//                }
//                if(position==1){
//                    im.setImageResource(R.drawable.icon2);
//
//                }
//                if(position==2){
//                    im.setImageResource(R.drawable.icon3);
//
//                }
//                if(position==3){
//                    im.setImageResource(R.drawable.icon4);
//
//                }
//


            if (im != null) {
                im.setText("" + p.getId());
            }



            if (tt1 != null) {
                tt1.setText(p.getTitle());
            }
            if (tt3 != null) {
                tt3.setText(p.getAuthor());
            }
            if (rb != null) {
                float rating = Float.parseFloat(p.getRating());
                rb.setRating(rating);
            }
        }

        return v;
    }
}

