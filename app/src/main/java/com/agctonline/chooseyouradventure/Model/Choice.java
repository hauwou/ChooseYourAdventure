package com.agctonline.chooseyouradventure.Model;

/**
 * Created by admin on 11/20/2014.
 *
 * create getters and setters by choosing "generate" from the "Code" menu
 *
 *In "settings" > codestyle > java > code generation, type in m in the prefix box
 * to avoid code generation to include the m in the method names
 *
 */
public class Choice {

    private String mText;
    private int mNextPage;

    public Choice(String text, int nextPage) {
        mText = text;
        mNextPage = nextPage;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        mNextPage = nextPage;
    }
}
