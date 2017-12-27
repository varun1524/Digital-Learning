package com.example.varun.connectdots;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by varun on 14/02/15.
 */

public class ConnectViewFour extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private Paint mPaint,paint;
    private static final int TOUCH_TOLERANCE_DP = 24;
    private static final int BACKGROUND = 00000000;
    private List<Point> mPoints = new ArrayList<Point>();
    private int mLastPointIndex = 0;
    private int mTouchTolerance;
    private boolean isPathStarted = false;
    int i=0;

    public ConnectViewFour(Context context) {
        super(context);
        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        paint=new Paint(1);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);

        //mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fill_puppy).copy(Bitmap.Config.ARGB_8888, true);

        // TODO just test points
        Point p1 = new Point(335,65);//0
        Point p2 = new Point(460,50);//1
        Point p3 = new Point(530,170);//2
        Point p4 = new Point(640,300);//3
        Point p5 = new Point(530,430);//4
        Point p6 = new Point(460,550);//5
        Point p7 = new Point(330,530);//6
        Point p8 = new Point(200,550);//7
        Point p9 = new Point(125,435);//8
        Point p10 = new Point(30,300);//9
        Point p11 = new Point(125,170);//10
        Point p12 = new Point(200,50);//11
        Point p13 = new Point(325,65);//12
        mPoints.add(p1);
        mPoints.add(p2);
        mPoints.add(p3);
        mPoints.add(p4);
        mPoints.add(p5);
        mPoints.add(p6);
        mPoints.add(p7);
        mPoints.add(p8);
        mPoints.add(p9);
        mPoints.add(p10);
        mPoints.add(p11);
        mPoints.add(p12);
        mPoints.add(p13);
    }


    public ConnectViewFour(Context context, AttributeSet attrs) {
        super(context, attrs);
        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        paint=new Paint(1);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
        // TODO just test points
      /*  Point p1 = new Point(490,390);
        Point p2 = new Point(400,330);
        Point p3 = new Point(410,250);
        Point p4 = new Point(510,240);
        Point p5 = new Point(470,150);
        Point p6 = new Point(580,50);
        Point p7 = new Point(690,150);
        Point p8 = new Point(650,240);
        Point p9 = new Point(750,250);
        Point p10 = new Point(760,330);
        Point p11 = new Point(670,390);*/
        Point p1 = new Point(240,380);
        Point p2 = new Point(150,330);
        Point p3 = new Point(160,250);
        Point p4 = new Point(220,220);
        Point p5 = new Point(220,150);
        Point p6 = new Point(330,50);
        Point p7 = new Point(440,150);
        Point p8 = new Point(440,220);
        Point p9 = new Point(500,250);
        Point p10 = new Point(510,330);
        Point p11 = new Point(420,380);
        mPoints.add(p1);
        mPoints.add(p2);
        mPoints.add(p3);
        mPoints.add(p4);
        mPoints.add(p5);
        mPoints.add(p6);
        mPoints.add(p7);
        mPoints.add(p8);
        mPoints.add(p9);
        mPoints.add(p10);
        mPoints.add(p11);
    }


    public ConnectViewFour(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mCanvas = new Canvas();
        mPath = new Path();
        mPaint = new Paint();
        paint=new Paint(1);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
        mTouchTolerance = dp2px(TOUCH_TOLERANCE_DP);
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        clear();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(BACKGROUND);
        canvas.drawBitmap(mBitmap, 0, 0, null);
        canvas.drawPath(mPath, mPaint);
        //canvas.drawLine(580,640,500,400,mPaint);
        //canvas.drawLine(580,640,660,400,mPaint);
        //canvas.drawLine(330,600,250,400,mPaint);
        //canvas.drawLine(330,600,410,400,mPaint);
        // TODO remove if you dont want points to be drawn
        canvas.drawCircle(330,300,100,mPaint);

        canvas.drawLine(330,200,330,80,mPaint);
        canvas.drawLine(330,400,330,520,mPaint);
        canvas.drawLine(420,250,520,180,mPaint);
        canvas.drawLine(420,360,520,430,mPaint);
        canvas.drawLine(235,250,135,180,mPaint);
        canvas.drawLine(235,355,135,430,mPaint);
        //Log.i("size of points",mPoints.size()+"");
        for (Point point : mPoints) {
            if(mPoints.size()==i){
                i=0;
            }
            try {
                String s = i + ".";
                canvas.drawText(s, point.x - 20, point.y, paint);
                i++;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }


        for (Point point : mPoints) {
            canvas.drawPoint(point.x, point.y, mPaint);
        }


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }


    private void touch_start(float x, float y) {

        if (checkPoint(x, y, mLastPointIndex)) {
            mPath.reset();
            // user starts from given point so path can beis started
            isPathStarted = true;
        } else {
            // user starts move from point which doen's belongs to mPinst list
            isPathStarted = false;
        }

    }


    //ADDED WITH LAST EDIT
    private void touch_move(float x, float y) {
        // draw line with finger move
        if (isPathStarted) {
            mPath.reset();
            Point p = mPoints.get(mLastPointIndex);
            mPath.moveTo(p.x, p.y);
            mPath.lineTo(x, y);
        }
    }


    private void touch_up(float x, float y) {
        mPath.reset();
        if (checkPoint(x, y, mLastPointIndex + 1) && isPathStarted) {
            // move finished at valid point so draw whole line
            // start point
            Point p = mPoints.get(mLastPointIndex);
            mPath.moveTo(p.x, p.y);
            // end point
            p = mPoints.get(mLastPointIndex + 1);
            mPath.lineTo(p.x, p.y);
            mCanvas.drawPath(mPath, mPaint);
            mPath.reset();
            // increment point index
            ++mLastPointIndex;

            isPathStarted = false;
        }
    }

    /**
     * Sets paint
     *
     * @param paint
     */


    public void setPaint(Paint paint) {
        this.mPaint = paint;
    }

    /**
     * Returns image as bitmap
     *
     * @return
     */
    public Bitmap getBitmap() {
        return mBitmap;
    }

    /**
     * Clears canvas
     */
    public void clear() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(BACKGROUND);
        mCanvas.setBitmap(mBitmap);
        invalidate();
    }

    /**
     * Checks if user touch point with some tolerance
     */
    private boolean checkPoint(float x, float y, int pointIndex) {
        if (pointIndex == mPoints.size()) {
            // out of bounds
            return false;
        }
        Point point = mPoints.get(pointIndex);
        //EDIT changed point.y to poin.x in the first if statement
        if (x > (point.x - mTouchTolerance) && x < (point.x + mTouchTolerance)) {
            if (y > (point.y - mTouchTolerance) && y < (point.y + mTouchTolerance)) {
                return true;
            }
        }
        return false;
    }

    public List<Point> getPoints() {
        return mPoints;
    }

    public void setPoints(List<Point> points) {
        this.mPoints = points;
    }

    private int dp2px(int dp) {
        Resources r = getContext().getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return (int) px;
    }
}
