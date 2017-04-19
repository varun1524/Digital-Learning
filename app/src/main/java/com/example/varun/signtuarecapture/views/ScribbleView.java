package com.example.varun.signtuarecapture.views;


import android.content.Context;
import android.graphics.*;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

//import com.cgit.slate.R;

/**
 * Created by Hiren Anuvadiya on 20/09/13.
 */
public class ScribbleView extends View {

    //private ArrayList<Bitmap> pagesBmp = new ArrayList<Bitmap>();
    private Paint notePaint;
    private Paint mBitmapPaint;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    public boolean isEraser = false;
    private boolean isReadOnly = false;
    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;
    private int mW = 800, mH = 480;
    private int penColor;

    //public MySQLiteHelper db=new MySQLiteHelper();
    private Path path = new Path();

    public ScribbleView(Context context) {
        super(context);
        init();
    }

    public ScribbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScribbleView(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
        init();
    }

    public void init() {
        setSize();
        newScrible();
        penColor = Color.BLACK;
        //penColor=getContext().getResources().getColor(R.color.pen_blue);
        notePaint = new Paint();
        notePaint.setAntiAlias(true);
        notePaint.setDither(true);
        notePaint.setColor(penColor);
        notePaint.setStyle(Paint.Style.STROKE);
        notePaint.setStrokeJoin(Paint.Join.ROUND);
        notePaint.setStrokeCap(Paint.Cap.ROUND);
        notePaint.setStrokeWidth(3);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);


    }

    public Bitmap createBitmap() {
        return Bitmap.createBitmap(mW, mH, Bitmap.Config.ARGB_4444);
    }

    public void newScrible() {
        mBitmap = createBitmap();
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        this.invalidate();

    }

    public void loadScrible(Bitmap bitmap) {
        mBitmap = bitmap;
        mCanvas = new Canvas(mBitmap);
        mPath = new Path();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        this.invalidate();
        // System.out.println("load called..");

        //return mCanvas;

    }

    public void setPenColor(int color) {
        penColor = color;

    }


    private void setSize() {
        int size[] = getScribbleDimension(false, this.getContext());
        mW = size[0];
        mH = size[1];
    }

    public void setPen() {
        notePaint.setColor(penColor);
        notePaint.setXfermode(null);
        notePaint.setStrokeWidth(3);
        isEraser = false;
    }

    public void setEraser() {
        notePaint.setColor(Color.TRANSPARENT);
        notePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        notePaint.setStrokeWidth(20);
        isEraser = true;

    }

    public void changePendMode() {
        if (notePaint != null) {
            if (isEraser) {
                setPen();
            } else {
                setEraser();
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawPath(mPath, notePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (!this.isReadOnly) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (isEraser) {
                        //notePaint.setColor(Color.GRAY);
                        notePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                        mCanvas.drawCircle(x, y, 10, notePaint);
                        invalidate();
                    }
                    touch_start(x, y);
                    invalidate();

                    break;
                case MotionEvent.ACTION_MOVE:
                    if (isEraser) {
                        //notePaint.setColor(Color.GRAY);
                        mCanvas.drawCircle(x, y, 20, notePaint);
                    } else {
                        touch_move(x, y);
                    }

                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    touch_up();
                    invalidate();
                    break;
            }
        }


        return true;
    }

    private void touch_start(float x, float y) {
        if (!isReadOnly) {
            mPath = new Path();
            mPath.moveTo(x, y);
            mX = x;
            mY = y;
        }

    }

    private void touch_move(float x, float y) {
        if (!isReadOnly) {
            float dx = Math.abs(x - mX);
            float dy = Math.abs(y - mY);
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
                mX = x;
                mY = y;
            }
        }

    }

    private void touch_up() {
        if (!isReadOnly) {
            mPath.lineTo(mX, mY);
            mCanvas.drawPath(mPath, notePaint);
        }
    }

    public File getImageDir() {
        String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyApp/Images/";

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();

        }
        return dir;
    }

    private String getTodaysDate() {

        final Calendar c = Calendar.getInstance();
        int todaysDate = (c.get(Calendar.YEAR) * 10000) +
                ((c.get(Calendar.MONTH) + 1) * 100) +
                (c.get(Calendar.DAY_OF_MONTH));
        Log.w("DATE:", String.valueOf(todaysDate));
        return (String.valueOf(todaysDate));

    }

    private String getCurrentTime() {

        final Calendar c = Calendar.getInstance();
        int currentTime = (c.get(Calendar.HOUR_OF_DAY) * 10000) +
                (c.get(Calendar.MINUTE) * 100) +
                (c.get(Calendar.SECOND));
        Log.w("TIME:", String.valueOf(currentTime));
        return (String.valueOf(currentTime));

    }


    public String save(View v) {
        String path="";
        try {


            //mCanvas = new Canvas(mBitmap);

            String filename = "" + getTodaysDate() + ":" + getCurrentTime() + ".jpeg";
            File file = new File(getImageDir(), filename);


            FileOutputStream mFileOutStream = new FileOutputStream(file);

            v.draw(mCanvas);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 85, mFileOutStream);

            mFileOutStream.flush();
            mFileOutStream.close();

            path=new String(filename);
            //saveInDB(path);

            //db.addImage(new Image(path));

            //System.out.println(path + "save view");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }







    public int[] getScribbleDimension(boolean isHalf, Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        int[] size = new int[2];
        if (isHalf) {
            size[0] = width / 2;
        } else {
            size[0] = width;
        }
        size[1] = height;
        return size;
    }


}

//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
////        Toast.makeText(getContext(),"On Size change called",Toast.LENGTH_SHORT).show();
//        AppLogger.infoLog("w:"+w+" h:"+h+" mBitmap:"+mBitmap);
//        if (w > 0 && h > 0) {
//            mW = w;
//		    mH = h;
//
//            Bitmap copy=null;
//            if(mBitmap!=null){
//                copy=mBitmap.copy(Bitmap.Config.ARGB_4444, true);
//            }
//            mBitmap = Bitmap.createBitmap(mW, mH, Bitmap.Config.ARGB_4444);
//            mCanvas = new Canvas(mBitmap);
//            if(copy!=null){
//                mCanvas.drawBitmap(copy, 0, 0, new Paint());
//            }
//            mPath = new Path();
//            mBitmapPaint = new Paint(Paint.DITHER_FLAG);
//
//        }
//        setPen();
//        invalidate();
//    }