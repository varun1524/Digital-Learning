package com.example.varun.fillcolor;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.varun.drawing.DrawingTask;

import com.example.varun.digitallearning.R;
import com.example.varun.signtuarecapture.ColorPickerDialog;

import java.util.LinkedList;
import java.util.Queue;

public class DrawColor extends Activity implements View.OnTouchListener,View.OnClickListener {
    private RelativeLayout drawingLayout;
    //ImageView imageView;
    private MyView myView;
    Paint paint;
    ImageButton imback,imcolor,imclear;
    ImageButton imflower,imfly,imdog,imhouse,imgirl,imboy;
    Intent intent;
    int clr=0;
    int count=0;
    Color mcolor;
    //Gallery gallery;
    //ImageAdapter imageAdapter;
    //ImageView imageView;
    //int p;
    int id=R.drawable.fill_flower;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_color);


        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //gallery=(Gallery)findViewById(R.id.gallery);
         /*     gallery.setAdapter(imageAdapter);

        gallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {
                //mySelection.setText(" selected option: " + position );
                p=position;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
            }

            public void onNothingSelected(AdapterView<?> parent) {
                //mySelection.setText("Nothing selected");

            }

            imageAdapter=new ImageAdapter(this);
        });*/


        myView = new MyView(this);
        drawingLayout = (RelativeLayout) findViewById(R.id.rlpic);
        //imageView=(ImageView)findViewById(R.id.coringImage);

        drawingLayout.addView(myView);

        imback=(ImageButton)findViewById(R.id.imback);
        imclear=(ImageButton)findViewById(R.id.imclear);
        imcolor=(ImageButton)findViewById(R.id.imclr);
        imflower=(ImageButton)findViewById(R.id.imflower);
        imfly=(ImageButton)findViewById(R.id.imfly);
        imdog=(ImageButton)findViewById(R.id.impuppy);
        imhouse=(ImageButton)findViewById(R.id.imhouse);
        imgirl=(ImageButton)findViewById(R.id.imgirl);
        imboy=(ImageButton)findViewById(R.id.imboy);

        imback.setOnClickListener(this);
        imclear.setOnClickListener(this);
        imcolor.setOnClickListener(this);
        imflower.setOnClickListener(this);
        imfly.setOnClickListener(this);
        imdog.setOnClickListener(this);
        imhouse.setOnClickListener(this);
        imgirl.setOnClickListener(this);
        imboy.setOnClickListener(this);

        mcolor=new Color();









    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imback:
                intent=new Intent(DrawColor.this,DrawingTask.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
                break;

            case R.id.imclear:
                paint.setColor(Color.WHITE);
                break;

            case R.id.imclr:
                count=count+2;
                new ColorPickerDialog(DrawColor.this, new ColorPickerDialog.OnColorChangedListener() {

                    public void colorChanged(int color) {
                        // TODO Auto-generated method stub
                        paint.setColor(color);
                        //clr=color;

                        //mColor1 = color;
                    }
                }, 0xFFFF0000).show();
                break;

            case R.id.imflower:
                id=R.drawable.fill_flower;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

            case R.id.imfly:
                id=R.drawable.fill_butterfly;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

            case R.id.impuppy:
                id=R.drawable.fill_puppy;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

            case R.id.imhouse:
                id=R.drawable.fill_house;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

            case R.id.imgirl:
                id=R.drawable.fill_girl;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

            case R.id.imboy:
                id=R.drawable.fill_boy;
                myView=new MyView(DrawColor.this);
                drawingLayout.addView((myView));
                break;

        }

    }

    public class MyView extends View {

        private Path path;
        Bitmap mBitmap;
        //ProgressDialog pd;
        final Point p1 = new Point();
        Canvas canvas;

        // Bitmap mutableBitmap ;
        public MyView(Context context) {
            super(context);
            paint = new Paint();
            paint.setAntiAlias(true);
            //pd = new ProgressDialog(context);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(5f);

           // id = setPicture();

            mBitmap = BitmapFactory.decodeResource(getResources(),id).copy(Bitmap.Config.ARGB_8888, true);

            this.path = new Path();

        }

      /*  private int setPicture() {
            switch (p){
                case 1:
                    id=R.drawable.fill_puppy;
                    break;
                case 2:
                    id=R.drawable.fill_flower;
                    break;
                case 3:
                    id=R.drawable.fill_butterfly;
                    break;
                case 4:
                    id=R.drawable.fill_house;
                    break;
                case 5:
                    id=R.drawable.fill_boy;
                    break;
                case 6:
                    id=R.drawable.fill_girl;
                    break;
                default:
                    id=R.drawable.fill_puppy;
                    break;

            }
            return id;
        }*/

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected void onDraw(Canvas canvas) {
            this.canvas = canvas;
            //canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.drawBitmap(mBitmap,0,0,paint);

            //imageView.setImageBitmap(mBitmap);

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    p1.x = (int) x;
                    p1.y = (int) y;
                    final int sourceColor = mBitmap.getPixel((int) x, (int) y);
                    final int targetColor = paint.getColor();
                    new TheTask(mBitmap, p1, sourceColor, targetColor).execute();
                    invalidate();
            }return true;
        }

        public void clear() {
            path.reset();
            invalidate();
        }

        public int getCurrentPaintColor() {
            return paint.getColor();
        }

        class TheTask extends AsyncTask<Void, Integer, Void> {

            Bitmap bmp;
            Point pt;
            int replacementColor, targetColor;

            public TheTask(Bitmap bm, Point p, int sc, int tc) {
                this.bmp = bm;
                this.pt = p;
                this.replacementColor = tc;
                this.targetColor = sc;
                //pd.setMessage("Filling....");
                //pd.show();
            }

            @Override
            protected void onPreExecute() {
                //pd.show();

            }
            @Override
            protected void onProgressUpdate(Integer... values) {

            }

            @Override
            protected Void doInBackground(Void... params) {
                FloodFill f = new FloodFill();
                f.floodFill(bmp, pt, targetColor, replacementColor);
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
               // pd.dismiss();
                invalidate();
            }
        }
    }

// flood fill

    public class FloodFill {

        public void floodFill(Bitmap image, Point node, int targetColor,
                              int replacementColor) {
            int width = image.getWidth();
            int height = image.getHeight();
            int target = targetColor;
            int replacement = replacementColor;
            if (target != replacement) {
                Queue<Point> queue = new LinkedList<Point>();
                do {
                    int x = node.x;
                    int y = node.y;
                    while (x > 0 && image.getPixel(x - 1, y) == target) {
                        x--;

                    }
                    boolean spanUp = false;
                    boolean spanDown = false;
                    while (x < width && image.getPixel(x, y) == target) {
                        image.setPixel(x, y, replacement);
                        if (!spanUp && y > 0
                                && image.getPixel(x, y - 1) == target) {
                            queue.add(new Point(x, y - 1));
                            spanUp = true;

                        } else if (spanUp && y > 0
                                && image.getPixel(x, y - 1) != target) {
                            spanUp = false;
                        }

                        if (!spanDown && y < height - 1
                                && image.getPixel(x, y + 1) == target) {
                            queue.add(new Point(x, y + 1));
                            spanDown = true;
                        }
                        else if (spanDown && y < height - 1
                                && image.getPixel(x, y + 1) != target) {
                            spanDown = false;
                        }

                        x++;
                    }
                } while ((node = queue.poll()) != null);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return false;
    }
}
