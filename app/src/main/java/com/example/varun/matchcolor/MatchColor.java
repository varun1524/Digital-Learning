package com.example.varun.matchcolor;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.varun.dialogchecker.DialogChecker;
import com.example.varun.drawing.DrawingTask;
import com.example.varun.digitallearning.R;
import java.util.ArrayList;



public class MatchColor extends Activity {
    /**
     * Called when the activity is first created.
     */

    public Button btn1,btn2,btn3;

    public ArrayList<Fruit> al;
    public Fruit f;
    public int c1,c2,c3,c4,correctColor;
    public int path;
    public GradientDrawable drawable;
    public Color1 co,co1;
    public DialogChecker dc;

    public int i=0;

    ImageView iv;
    MediaPlayer mp;
    ImageButton iback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_color);

        // mp=MediaPlayer.create(MyActivity.this,R.raw.song);
        //mp.start();
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        btn1=(Button)findViewById(R.id.bt1);
        btn2=(Button)findViewById(R.id.bt2);
        btn3=(Button)findViewById(R.id.bt3);
        iback=(ImageButton)findViewById(R.id.btnback);


        iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MatchColor.this, DrawingTask.class);
                startActivity(intent);
                MatchColor.this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        });

        iv=(ImageView)findViewById(R.id.iv1);

        //iv.setBackgroundResource(R.drawable.image_border);

        btn1.setBackgroundResource(R.drawable.button_1);
        btn2.setBackgroundResource(R.drawable.button_1);
        btn3.setBackgroundResource(R.drawable.button_1);

        al=new ArrayList<Fruit>();



        fill();


        retrieve(i);
    }

    public void fill()
    {


      /*  c1=-16777216;
        c2=-65536;
        c3=-16711681;
        c4=-65536;
*/



        al.add(new Fruit("#FF2BFF0E","#FFFF221D","#FF3317FF","#FFFF221D",R.drawable.mc_tomato,R.raw.red));
        al.add(new Fruit("#FFFDFF39","#FFD57DFF","#FFFF6F52","#FFFDFF39",R.drawable.mc_yellow,R.raw.yellow));
        al.add(new Fruit("#FFFF5CCA","#FFFF221D","#FF3130FF","#FF3130FF",R.drawable.mc_bluecar2,R.raw.blue));
        al.add(new Fruit("#FFFF7C1A","#FF3FFF30","#FF40FFE2","#FFFF7C1A",R.drawable.mc_orange,R.raw.orange));
        al.add(new Fruit("#FF4843FF","#FF22FF2B","#FFFF5C5C","#FF22FF2B",R.drawable.mc_greencar,R.raw.green));
        al.add(new Fruit("#FFFF168F","#FFFFFC3B","#FFFF221D","#FFFF168F",R.drawable.mc_pink,R.raw.pink));




      /*  al.add(new Fruit("#FF2BFF0E","#FFFF221D","#FF3317FF","#FFFF221D",R.drawable.ic_apple1));
        al.add(new Fruit("#FFFFFC3B","#FFD57DFF","#FFFF6F52","#FFFFFC3B",R.drawable.ic_banana));
        al.add(new Fruit("#FFFF5CCA","#FFFF221D","#FF3130FF","#FF3130FF",R.drawable.ic_carblue));
        al.add(new Fruit("#FFFF7C1A","#FF3FFF30","#FF40FFE2","#FFFF7C1A",R.drawable.ic_orange));
        al.add(new Fruit("#FF4843FF","#FFDCFF45","#FFFF5C5C","#FFDCFF45",R.drawable.ic_grapes));
        al.add(new Fruit("#FFFF168F","#FFFFFC3B","#FFFF221D","#FFFF168F",R.drawable.ic_flowerpink));
        */

    }

    public void changeView()
    {
        //int c4= retrieve(i);
        //final int gc=0;


        btn1.setOnClickListener(new View.OnClickListener() {
            int c;
            @Override
            public void onClick(View view) {

                //view.setBackgroundResource(R.drawable.button_1);

                // drawable = (GradientDrawable) btn1.getBackground();



                // drawable.getB


                //int c = drawable();

                // c= buttonColor.getColor();

                co1=(Color1)btn1.getTag();

                c=co1.getColor();

                //Integer i=(Integer)o;

                //int c=i.intValue();

                check(c);



            }


        });
        btn2.setOnClickListener(new View.OnClickListener() {
            int c;
            @Override
            public void onClick(View view) {
                co1=(Color1)btn2.getTag();

                c=co1.getColor();


                check(c);


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            int c;
            @Override
            public void onClick(View view) {
                co1=(Color1)btn3.getTag();

                c=co1.getColor();


                check(c);

            }

        });

    }

    public void check(int c)
    {
        try {
            System.out.println("i"+ i);
            System.out.println("al.size"+ al.size());




            // System.out.println("cg"+cg);
            //changeView();

            System.out.println("color===" + c);

            boolean l = (correctColor == c);
            System.out.println(l);

            if (c == correctColor) {
                int resId=f.getColorName();

                mp=MediaPlayer.create(MatchColor.this,resId);
                mp.start();

                dc=new DialogChecker(this);

                dc.showCorrect();

                i = i + 1;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        retrieve(i);
                    }
                }, 1000);




            }
            if (!(c == correctColor)) {

                dc=new DialogChecker(this);
                dc.showInCorrect();
            }
            if (i >= al.size()) {
                Toast.makeText(this, "Successfully completed..", Toast.LENGTH_SHORT).show();
                // player.stop();
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void retrieve(int i)
    {

        try {

            if(i<=al.size()) {
                f = al.get(i);
            }

            else
            {
                System.out.println("Finished..");
            }

            int c1 = f.getColor1();
            int c2 = f.getColor2();
            int c3 = f.getColor3();
            correctColor = f.getColor4();
            path = f.getPath();

            System.out.println("cg in retrieve" + correctColor);

            iv.setBackgroundResource(path);

            drawable = (GradientDrawable) btn1.getBackground();
            drawable.setColor(c1);


            drawable = (GradientDrawable) btn2.getBackground();
            drawable.setColor(c2);


            drawable = (GradientDrawable) btn3.getBackground();
            drawable.setColor(c3);



            co=new Color1(c1);
            btn1.setTag(co);

            co=new Color1(c2);
            btn2.setTag(co);

            co=new Color1(c3);
            btn3.setTag(co);
            //    btn3.setBackgroundColor(c3);


            // return c4;
            changeView();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }






}
