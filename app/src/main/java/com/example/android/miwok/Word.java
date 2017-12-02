package com.example.android.miwok;

/**
 * Created by zheng on 2017/12/1.
 */

public class Word {
    // String value
    private String miwokWord;

    private String defaultWord;

    // Text color
    private int mMiwokColor;
    private int mDefaultColor;

    /**
     * Constructs a new TextView with initial values for text and text color.
     */
    public Word(String defaultText,String miwokText) {
        defaultWord = defaultText;
        miwokWord = miwokText;
        mMiwokColor = 0;
        mDefaultColor = 0;
    }

    /**
     * Sets the text color of miwokWord text.
     *
     * @param color of text to be displayed.
     */
    public void setMiwokColor(int color) {
        mMiwokColor = color;
    }

    /**
     * Sets the text color of the defaultWord text.
     *
     * @param color of text to be displayed.
     */
    public void setDefaultColor(int color) {
        mDefaultColor = color;
    }

    /**
     * Gets the string value of defaultWord.
     *
     * @return current text of defaultWord.
     */
    public String getDefaultWord() {
        return defaultWord;
    }

    /**
     * Gets the string value of miwokWord.
     *
     * @return current text of miwokWord.
     */
    public String getMiwokWord() {
        return miwokWord;
    }
}
