package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(@NonNull Context context, @NonNull List<Article> objects) {
        super(context, 0, objects);
    }

    /**
     * Returns a list item view that displays information about the article at the given position
     * in the list of articles.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String author;
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        // Find the article at the given position in the list of earthquakes
        Article currentArticle = getItem(position);

        // Find the TextView with view ID title
        TextView titleView = listItemView.findViewById(R.id.title);
        // Display the title of the current article in that TextView
        titleView.setText(currentArticle.getTitle());

        // Find the TextView with the view ID section_name
        TextView sectionNameView = listItemView.findViewById(R.id.section_name);
        // Display the section name of the current Article
        sectionNameView.setText(currentArticle.getSection());

        // Check whether the author string is not empty
        if (!currentArticle.getAuthor().isEmpty()) {
            author = "by " + currentArticle.getAuthor();
        } else {
            author = "No Author Name Found";
        }

        // Find the TextView with view ID author
        TextView authorView = listItemView.findViewById(R.id.author);
        // Display the location of the current earthquake in that TextView
        authorView.setText(author);

        // Create a new Date object from the time in ISO Date format
        // Find the TextView with view ID date
        TextView dateView = listItemView.findViewById(R.id.date);
        // Format the date string (i.e. "03-05-1984, 20:23")
        String date = currentArticle.getDate();
        String formattedDate = "Pub.: " + formatDate(date);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private String formatDate(String dateObject) {
        Date formattedDate = null;
        // if timezone is required
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            formattedDate = dateFormat.parse(dateObject);
            dateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(formattedDate);
    }
}
