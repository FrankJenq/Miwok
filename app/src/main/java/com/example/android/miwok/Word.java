package com.example.android.miwok;

/**
 * Created by zheng on 2017/12/1.
 */

public class Word {
    // String value

    private int mVoiceId;

    private String mMiwokWord;

    private String mDefaultWord;

    private int mImageResourceId = NO_IMAGE;

    private static final int NO_IMAGE = -1;

    /**
     * Constructs a new object with two Texts.
     */
    public Word(int voiceId, String defaultText, String miwokText) {
        mVoiceId = voiceId;
        mDefaultWord = defaultText;
        mMiwokWord = miwokText;
    }

    /**
     * Constructs a new object with two Texts and one ImageViewId.
     */
    public Word(int voiceId, String defaultText, String miwokText, int imageId) {
        mVoiceId = voiceId;
        mDefaultWord = defaultText;
        mMiwokWord = miwokText;
        mImageResourceId = imageId;
    }

    public String getDefaultWord() {
        return mDefaultWord;
    }

    /**
     * Gets the string value of miwokWord.
     *
     * @return current text of miwokWord.
     */
    public String getMiwokWord() {
        return mMiwokWord;
    }

    /**
     * Gets the ID of the picture.
     */
    public int getImageResourceId() {
        return mImageResourceId;
    }

    /**
     * @return the ID of the pronunciation voice.
     */
    public int getVoiceId(){return mVoiceId;}

    /**
     * @return whether there is a valid image.
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE;
    }
}
