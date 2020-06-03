package com.degroff.pandaled.ui.main.fragment.content;

/**
 * A Pattern item representing a LED Pattern.
 */
public class ListItem
    {
    public final String id;
    public final String prefix;
    public final String content;
    public final String details;
    public final int drawable;
    public final int bleChar;

    public ListItem(final String prefix, final String id, final String content, final String details, final int bleChar)
        {
        this.prefix = prefix;
        this.id = id;
        this.content = content;
        this.details = details;
        this.bleChar = bleChar;
        this.drawable = 0;
        }

    public ListItem(final String prefix, final String id, final String content, final String details, final int drawable, final int bleChar)
        {
        this.prefix = prefix;
        this.id = id;
        this.content = content;
        this.details = details;
        this.drawable = drawable;
        this.bleChar = bleChar;
        }

    @Override
    public String toString()
        {
        return content;
        }
    }