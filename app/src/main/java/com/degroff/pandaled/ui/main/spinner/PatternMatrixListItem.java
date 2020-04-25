package com.degroff.pandaled.ui.main.spinner;

public class PatternMatrixListItem
    {
    private final String text;
    private final int imgID;
    private final int imgIDDark;

    public PatternMatrixListItem(final String text, final int imgID, final int imgIDDark)
        {
        this.text = text;
        this.imgID = imgID;
        this.imgIDDark = imgIDDark;
        }

    public String getText()
        {
        return text;
        }

    public int getImgID()
        {
        return imgID;
        }

    public int getImgIDDark()
        {
        return imgIDDark;
        }
    }
