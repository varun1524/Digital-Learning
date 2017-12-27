package com.example.varun.matchshapes;

/**
 * Created by varun on 26/02/15.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.varun.dialogchecker.DialogChecker;
import com.example.varun.drawing.DrawingTask;
import com.example.varun.digitallearning.R;

import java.util.ArrayList;


public class MatchShapes extends Activity implements View.OnDragListener,View.OnLongClickListener,View.OnClickListener{
    String msg;
    int count=0;
    ImageView sq, cir, tri,sq1,tri1,cir1;
    ImageButton btnback,msnext,msback;
    Intent intent;
    Boolean flag1=false,flag2=false,flag3=false;
    ArrayList<Shapes> al;
    int i=0;
    Shapes shapes;
    int id1,id2,id3;
    DialogChecker d;
    Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchshapes);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        btnback=(ImageButton)findViewById(R.id.btnback);
        sq1 = (ImageView) findViewById(R.id.mshape1);
        cir1 = (ImageView) findViewById(R.id.mshape2);
        tri1 = (ImageView) findViewById(R.id.mshape3);
        sq = (ImageView) findViewById(R.id.sideshape1);
        cir = (ImageView) findViewById(R.id.sideshape2);
        tri = (ImageView) findViewById(R.id.sideshape3);
        //msnext=(ImageButton)findViewById(R.id.msnext);
        //msback=(ImageButton)findViewById(R.id.msback);
        //msback.setVisibility(View.INVISIBLE);

       // sq1.setImageResource(R.drawable.match_circle);
       // cir1.setImageResource(R.drawable.match_rect);
        //tri1.setImageResource(R.drawable.match_sc);

        //sq.setImageResource(R.drawable.match_circle);
        //cir.setImageResource(R.drawable.match_rect);
        //tri.setImageResource(R.drawable.match_sc);

        btnback.setOnClickListener(this);

        //msnext.setOnClickListener(this);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds


        al=new ArrayList<Shapes>();
        fill();
        retrieve(i);
    }

    private void fill() {
        al.add(new Shapes(R.drawable.match_circle,R.drawable.match_rect,R.drawable.match_sc));
        al.add(new Shapes(R.drawable.match_sc,R.drawable.match_sq,R.drawable.match_tri));
        al.add(new Shapes(R.drawable.match_tri,R.drawable.match_star,R.drawable.match_sq));
        al.add(new Shapes(R.drawable.match_star,R.drawable.match_tri,R.drawable.match_rect));
    }

    private void retrieve(int i) {
        if(i<al.size()){
            shapes=al.get(i);
        }

        id1=shapes.getId1();
        id2=shapes.getId2();
        id3=shapes.getId3();

        sq1.setImageResource(id1);
        cir1.setImageResource(id2);
        tri1.setImageResource(id3);

        sq.setImageResource(id1);
        cir.setImageResource(id2);
        tri.setImageResource(id3);

        flag1=flag2=flag3=false;

        sq.setOnLongClickListener(this);
        cir.setOnLongClickListener(this);
        tri.setOnLongClickListener(this);
        sq1.setOnDragListener(this);
        cir1.setOnDragListener(this);
        tri1.setOnDragListener(this);


    }


    @Override
    public boolean onDrag(View v, DragEvent e) {
        switch (e.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:

                if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                    // view.setColorFilter(Color.BLUE);
                    v.invalidate();
                    return true;
                }
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
                int x_cord = (int) e.getX();
                int y_cord = (int) e.getY();
                // v.setColorFilter(Color.GREEN);
                Log.i("coordinates:x:",x_cord+"");

                Log.i("coordinates:y:",y_cord+"");
                v.invalidate();
                return true;
            //break;

            case DragEvent.ACTION_DRAG_EXITED:

                Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");

                v.invalidate();
                x_cord = (int) e.getX();
                y_cord = (int) e.getY();
                Log.i("coordinates:x:",x_cord+"");

                Log.i("coordinates:y:",y_cord+"");
                break;

            case DragEvent.ACTION_DRAG_LOCATION:

                Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
                x_cord = (int) e.getX();
                y_cord = (int) e.getY();
                Log.i("coordinates:x:",x_cord+"");

                Log.i("coordinates:y:",y_cord+"");
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
                // Turns off any color tinting
                // Invalidates the view to force a redraw
                // Does a getResult(), and displays what happened.


                if (e.getResult()) {
                    Toast.makeText(this, "The drop was handled.", Toast.LENGTH_LONG);

                } else {
                    Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_LONG);

                }
                v.invalidate();
                // returns true; the value is ignored.
                return true;



            case DragEvent.ACTION_DROP:

                Log.d(msg, "ACTION_DROP event");
                // Do nothing
                x_cord = (int) e.getX();
                y_cord = (int) e.getY();
                Log.i("coordinates:x:",x_cord+"");

                Log.i("coordinates:y:",y_cord+"");



                switch (count){

                    case 1:
                        if(R.id.mshape1==v.getId()){
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                            Log.i("check","success");
                            sq.setImageDrawable(null);
                            sq.setOnLongClickListener(null);
                            flag1=true;
                        }else {
                            Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_SHORT).show();
                            Log.i("check","fails");
                        }
                        break;

                    case 2:
                        if(R.id.mshape2==v.getId()){
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                            Log.i("check","success");
                            cir.setImageDrawable(null);
                            cir.setOnLongClickListener(null);
                            flag2=true;
                        }else {
                            Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_SHORT).show();
                            Log.i("check","fails");
                        }
                        break;

                    case 3:
                        if(R.id.mshape3==v.getId()){
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                            Log.i("check","success");
                            tri.setImageDrawable(null);
                            tri.setOnLongClickListener(null);
                            flag3=true;
                        }else {
                            Toast.makeText(getApplicationContext(),"unsuccessful",Toast.LENGTH_SHORT).show();
                            Log.i("check","fails");
                        }
                        break;

                }

            default:
                check();
                break;

        }



    return false;

    }

    private void check() {


        if (flag1 == true && flag2 == true && flag3 == true) {
            d = new DialogChecker(this);
            d.showCorrect();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //timer
                    if (i < al.size()) {
                        i++;

                    }
                    retrieve(i);
                }
            }, 1000);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
        ClipData data = ClipData.newPlainText("", "");
        vibrator.vibrate(500);
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
        // Starts the drag
        v.startDrag(data,  // the data to be dragged
                shadowBuilder,  // the drag shadow builder
                null,      // no need to use local data
                0          // flags (not currently used, set to 0)
        );


        switch (v.getId()){
            case R.id.sideshape1:
                //Log.i("picked image is: ","square");
                count=1;
                break;
            case R.id.sideshape2:
                //Log.i("picked image is: ","circle");
                count=2;
                break;
            case R.id.sideshape3:
                //Log.i("picked image is: ","triangle");
                count=3;
                break;
        }
        return true;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnback:
                intent=new Intent(this,DrawingTask.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
                break;

            /*case R.id.msnext:
                if(flag1==flag2==flag3==true) {
                    intent = new Intent(this, MatchShapesTwo.class);
                    startActivity(intent);
                    this.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
                break;*/

            /*case R.id.msback:
                intent=new Intent(this,MatchShapes.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
                break;*/
        }

    }

}
