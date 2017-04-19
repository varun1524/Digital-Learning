package com.example.varun.digitallearning;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by PANAM SHAH on 06-02-2015.
 */
public class FEditText extends EditText {

    private Rect mRect;
    private Paint mPaint;
    //private Canvas canvas;

    private static final String FONT_FILE = "Herculanum.ttf";


    public FEditText(Context context) {
        super(context);


    }


    public FEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        int aColor = Color.parseColor("#00bfff");
        mPaint.setColor(aColor);
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

    protected void onDraw(Canvas canvas) {

        int height = getHeight();
        int line_height = getLineHeight();
        int count = height / line_height;
        if(getLineCount() > count){
            count = getLineCount();
        }
        Rect r = mRect;
        Paint paint = mPaint;
        int baseline = getLineBounds(0, r);
        //for (int i = 0; i < count-1; i++) {
            canvas.drawLine(r.left,baseline+5, r.right,baseline+5, paint);
            baseline += getLineHeight();
        //}
        super.onDraw(canvas);
    }


}
