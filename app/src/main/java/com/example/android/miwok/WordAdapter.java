package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zheng on 2017/12/1.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    // The color of each main layout
    private int mBackgoundColor;

    public WordAdapter(Activity context, ArrayList<Word> Words,int color) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, Words);
        this.mBackgoundColor = color;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // The MediaPlayer used in every list item
        final MediaPlayer musicPlayer;

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultWordTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the English word from the current Word object and
        // set this text on the defaultWord TextView
        defaultWordTextView.setText(currentWord.getDefaultWord());

        // Find the miwok word in the list_item.xml layout
        TextView miwokWordTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok word from the current Word object and
        // set this text on the miwokWord TextView TextView
        miwokWordTextView.setText(currentWord.getMiwokWord());

        // set the color of the text and the back arrow
        View textLayout = listItemView.findViewById(R.id.text_layout);
        View arrowLayout = listItemView.findViewById(R.id.play_arrow_layout);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mBackgoundColor);
        // Set the background color of the text container View
       textLayout.setBackgroundColor(color);
       arrowLayout.setBackgroundColor(color);

        // Find the ImageView in the list_item.xml layout with the ID
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.icon_view);
        //Whether there is a valid image
        if (currentWord.hasImage()) {
            // if so, get the image resource ID from the current Word object and
            // set the image to iconView
            iconView.setImageResource(currentWord.getImageResourceId());
            iconView.setVisibility(View.VISIBLE);
        }
        //Otherwise, set the ImageView invisible
        else
        {
            iconView.setVisibility(View.GONE);
        }

        // Link the ImageButton and the voice
//        musicPlayer =MediaPlayer.create(this.getContext(), currentWord.getVoiceId());
//        ImageButton playButton = (ImageButton) listItemView.findViewById(R.id.play_button);
//        playButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                musicPlayer.start();
//            }
//        });

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }
}
