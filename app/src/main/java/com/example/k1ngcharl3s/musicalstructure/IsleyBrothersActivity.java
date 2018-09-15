package com.example.k1ngcharl3s.musicalstructure;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class IsleyBrothersActivity extends AppCompatActivity {

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

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a list of songs
        final ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(" Isley Brothers", "\n Hello", R.drawable.isleybrothers, R.raw.hello));
        songs.add(new Song(" Isley Brothers", "\n Voyage To Atlantis", R.drawable.isleybrothers, R.raw.voyage_to_atlantis));
        songs.add(new Song(" Isley Brothers", "\n For The Love of You", R.drawable.isleybrothers, R.raw.for_the_love_of_you));
        songs.add(new Song(" Isley Brothers", "\n Choosey Lover", R.drawable.isleybrothers, R.raw.choosey_lover));
        songs.add(new Song(" Isley Brothers", "\n Between The Sheets", R.drawable.isleybrothers, R.raw.between_the_sheets));
        songs.add(new Song(" Isley Brothers", "\n Summer Breeze", R.drawable.isleybrothers, R.raw.summer_breeze));
        songs.add(new Song(" Isley Brothers", "\n I Once Had Your Love", R.drawable.isleybrothers, R.raw.once_had_your_love));
        songs.add(new Song(" Isley Brothers", "\n Brown Eyed Girl", R.drawable.isleybrothers, R.raw.brown_eyed_girl));
        songs.add(new Song(" Isley Brothers", "\n Fall In Love", R.drawable.isleybrothers, R.raw.fall_in_love));
        songs.add(new Song(" Isley Brothers", "\n Make Me Say", R.drawable.isleybrothers, R.raw.make_me_say));
        songs.add(new Song(" Isley Brothers", "\n Goodnight", R.drawable.isleybrothers, R.raw.goodnight));

        // Create an {@link SongAdapter}, whose data source is a list of {@link songs}s. The
        // adapter knows how to create list items for each item in the list.
        SongAdapter adapter = new SongAdapter(this, songs);
        ListView songsListView = findViewById(R.id.list);
        songsListView.setAdapter((ListAdapter) adapter);
        songsListView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(android.widget.AdapterView<?> adapterView, android.view.View view, int position, long l) {
                Song song = songs.get(position);
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(IsleyBrothersActivity.this, song.getAudioResourceId());
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                mMediaPlayer.start();
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
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
