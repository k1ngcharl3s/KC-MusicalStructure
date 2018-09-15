package com.example.k1ngcharl3s.musicalstructure;

/**
 * {@link Song} represents a song that the user wants to listen.
 * It contains resource IDs for the  audio files and
 * optional image file for that song and artist.
 */

class Song {
    /** The Artist of each song */
    private String mArtist;
    /** The Name of each song */
    private String mSong;
    /** Audio resource ID for the song */
    private int mAudioResourceId;
    /** Image resource ID for the song */
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    /** Constant value that represents no image was provided for the song */
    private static final int NO_IMAGE_PROVIDED = -1;



/*
    @Override
    public String toString() {
        return "Song{" +
                "mArtist='" + mArtist + '\'' +
                ", mSong='" + mSong + '\'' +
                ", mAudioResourceId=" + mAudioResourceId +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }

*/
    /**
     * Create a new Song object.
     *
     * @param artist is the string resource ID for the song
     * @param song is the string resource ID for the song
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */

    public Song(String artist, String song, int audioResourceId) {
        mArtist = artist;
        mSong = song;
        mAudioResourceId = audioResourceId;
    }



    /**
     * Create a new Song object.
     *
     * @param artist is the string resource ID for the song.
     * @param song is the string resource Id for the song
     * @param imageResourceId is the drawable resource ID for the image associated with the song
     * @param audioResourceId is the resource ID for the audio file associated with this song
     */
    public Song(String artist, String song, int imageResourceId, int audioResourceId) {
        mArtist = artist;
        mSong = song;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Return the artist of the song.
     */
    public String getArtistName() {
        return mArtist;
    }

    /**
     * Return the image song title of each song.
     */
    public String getSongTitle() {
        return mSong;
    }

    /**
     * Return the image resource ID of the song.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * Returns whether or not there is an image for this song.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    /**
     * Return the audio resource ID of the song.
     */
    public int getAudioResourceId() { return mAudioResourceId;}
}

