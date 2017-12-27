package com.example.varun.digitallearning;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by PANAM SHAH on 06-02-2015.
 */
public class FTextView extends TextView {

    private static final String FONT_FILE = "Herculanum.ttf";

    public FTextView(Context context) {
        super(context);

    }

    public FTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            if (!isInEditMode()) {
                TypedArray a = getContext().obtainStyledAttributes(
                        attributeSet,
                        R.styleable.FTextView);

                String fontName = a.getString(R.styleable.FTextView_fontName);
                if (fontName != null) {
                    Typeface font = Typeface.createFromAsset(getContext().getAssets(), fontName);
                    setTypeface(font);
                }
                a.recycle();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
