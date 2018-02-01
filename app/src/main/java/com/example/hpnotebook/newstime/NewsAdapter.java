package com.example.hpnotebook.newstime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hp Notebook on 29-01-2018.
 */

public class NewsAdapter extends ArrayAdapter<News>{

    private static final String LOG_TAG = NewsAdapter.class.getName();

    public NewsAdapter(Activity context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_news, parent, false);
        }

        final News currentNews = getItem(position);

        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentNews.getSection());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        String imgUri = currentNews.getUrl();
        Picasso.with(getContext()).load(imgUri)
                .into(imageView);

        TextView headingView = (TextView) listItemView.findViewById(R.id.heading);
        headingView.setText(currentNews.getHeading());

        TextView detailsView = (TextView) listItemView.findViewById(R.id.details);
        detailsView.setText(currentNews.getDetails());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String date = formatDate(currentNews.getDate());
        dateView.setText(date);

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        if(authorView != null){
            authorView.setText(currentNews.getAuthor());
        }
        else{
            authorView.setVisibility(View.GONE);
        }


        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webPage = Uri.parse(currentNews.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                getContext().startActivity(webIntent);
            }
        });

        return listItemView;
    }

    private String formatDate(String date){
        Date dateObject = new Date();
        try{
            dateObject = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(date);
        }
        catch (ParseException e){
            Log.e("NewsAdapter", "Problem parsing the date", e);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject).toString();
    }
}
