package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        super.onCreate(savedInstanceState);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word(R.raw.color_red, "red", "weṭeṭṭi", R.drawable.color_red));
        words.add(new Word(R.raw.color_green, "green", "chokokki", R.drawable.color_green));
        words.add(new Word(R.raw.color_brown, "brown", "ṭakaakki", R.drawable.color_brown));
        words.add(new Word(R.raw.color_gray, "gray", "ṭopoppi", R.drawable.color_gray));
        words.add(new Word(R.raw.color_black, "black", "kululli", R.drawable.color_black));
        words.add(new Word(R.raw.color_white, "white", "kelelli", R.drawable.color_white));
        words.add(new Word(R.raw.color_dusty_yellow, "dusty yellow", "ṭopiisə", R.drawable.color_dusty_yellow));
        words.add(new Word(R.raw.color_mustard_yellow, "mustard yellow", "chiwiiṭə", R.drawable.color_mustard_yellow));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_listyout file.

        final ListView listView = (ListView) rootView.findViewById(R.id.list);

        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        // send a request to get the status of audio focus.


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // clean up the media player when another audio is to be played.
                releaseMediaPlayer();
                // initialize the media player with the target audio, then play the audio
                // and release the media player when the audio playing is done.
                int result = mAudioManager.requestAudioFocus(afChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(getActivity(), words.get(position).getVoiceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }
            }
        });

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);
        return rootView;
    }

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    // Pause playback because your Audio Focus was
                    // temporarily stolen, but will be back soon.
                    // i.e. for a phone call
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    // Stop playback, because you lost the Audio Focus.
                    // i.e. the user started some other playback app
                    // Remember to unregister your controls/buttons here.
                    // And release the kra — Audio Focus!
                    // You’re done.
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                        mAudioManager.abandonAudioFocus(afChangeListener);
                    }
                    // Lower the volume, because something else is also
                    // playing audio over you.
                    // i.e. for notifications or navigation directions
                    // Depending on your audio playback, you may prefer to
                    // pause playback here instead. You do you.
                    else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    // Resume playback, because you hold the Audio Focus
                    // again!
                    // i.e. the phone call ended or the nav directions
                    // are finished
                    // If you implement ducking and lower the volume, be
                    // sure to return it to normal here, as well.
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    }
                }
            };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            //mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);

            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }

    // get back the main activity when the back button is pressed.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}