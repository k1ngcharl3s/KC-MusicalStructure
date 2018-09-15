package com.example.k1ngcharl3s.musicalstructure;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TupacActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /** Handles audio focus when playing a sound file */
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new android.media.MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(android.media.MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of songs
        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(" Tupac", "\n Picture Me Rollin", R.drawable.tupac1, R.raw.picture_me_rollin));
        songs.add(new Song(" Tupac", "\n Holla At Me", R.drawable.tupac1, R.raw.holla_at_me));
        songs.add(new Song(" Tupac", "\n White Man'z World", R.drawable.tupac1, R.raw.white_manz_world));
        songs.add(new Song(" Tupac", "\n Skandalouz", R.drawable.tupac1, R.raw.skandalouz));
        songs.add(new Song(" Tupac", "\n No More Pain", R.drawable.tupac1, R.raw.no_more_pain));
        songs.add(new Song(" Tupac", "\n Heartz of Men?", R.drawable.tupac1, R.raw.heartz_of_men));
        songs.add(new Song(" Tupac", "\n Only God Can Judge Me", R.drawable.tupac1, R.raw.only_god_can_judge_me));
        songs.add(new Song(" Tupac", "\n Tradin' War Stories?", R.drawable.tupac1, R.raw.tradin_war_stories));
        songs.add(new Song(" Tupac", "\n Life's SO Hard", R.drawable.tupac1, R.raw.lifes_so_hard));
        songs.add(new Song(" Tupac", "\n Staring Through My Rearview", R.drawable.tupac1, R.raw.staring_through_my_rearview));
        songs.add(new Song(" Tupac", "\n If I Die 2Nite", R.drawable.tupac1, R.raw.if_i_die_tonite));
        songs.add(new Song(" Tupac", "\n Temptations", R.drawable.tupac1, R.raw.temptations));
        songs.add(new Song(" Tupac", "\n Unconditional Love", R.drawable.tupac1, R.raw.unconditional_love));

        // Create an {@link SongAdapter}, whose data source is a list of {@link songs}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // word_list.xml layout file.
        ListView songsListView = findViewById(R.id.list);

        // Set a click listener to play the audio when the list item is clicked on
        songsListView.setAdapter((ListAdapter) adapter);
        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);
                releaseMediaPlayer();

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.

                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current song
                mMediaPlayer = MediaPlayer.create(TupacActivity.this, song.getAudioResourceId());

                // Request audio focus so in order to play the audio file. The app needs to play a
                // short audio file, so we will request audio focus with a short amount of time
                // with AUDIOFOCUS_GAIN_TRANSIENT.
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                mMediaPlayer.start();
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);

                    // Start the audio file
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
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
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
