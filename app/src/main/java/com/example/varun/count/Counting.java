package com.example.varun.count;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.varun.dialogchecker.DialogChecker;
import com.example.varun.drawing.DrawingTask;

import com.example.varun.digitallearning.R;

import java.util.ArrayList;

/**
 * Created by varun on 25/02/15.
 */

public class Counting extends Activity implements View.OnClickListener {
    EditText editText;
    String s;
    ImageButton imback,im1,im2,im3,cnext,cback;
    ImageView imageView;
    Intent intent;
    Boolean flag=false;
    ArrayList<CountDemo> al;
    DialogChecker dialogChecker;
    int i=0;
    CountDemo cd;
    int id1,id2,id3,bg,v1,v2,v3,vbg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.counting);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        imageView=(ImageView)findViewById(R.id.count_image);
        im1=(ImageButton)findViewById(R.id.ibcount1);
        im2=(ImageButton)findViewById(R.id.ibcount2);
        im3=(ImageButton)findViewById(R.id.ibcount3);
        imback=(ImageButton)findViewById(R.id.btnback);
        //cnext=(ImageButton)findViewById(R.id.cnext);
        //cback=(ImageButton)findViewById(R.id.cback);
        //cback.setVisibility(View.INVISIBLE);

        imback.setOnClickListener(this);
        //cback.setOnClickListener(this);
        //cnext.setOnClickListener(this);


//        im1.setImageResource(R.drawable.c_eight);
  //      im2.setImageResource(R.drawable.c_three);
    //    im3.setImageResource(R.drawable.c_one);
      //  imageView.setImageResource(R.drawable.count_pic1);

        al=new ArrayList<CountDemo>();
        fill();
        retrieve(i);
    }



    private void fill() {

        al.add(new CountDemo(R.drawable.c_eight,8,R.drawable.c_three,3,R.drawable.c_one,1,R.drawable.count_pic1,3));
        al.add(new CountDemo(R.drawable.c_five,5,R.drawable.c_three,3,R.drawable.c_nine,9,R.drawable.count_pic2,5));
        al.add(new CountDemo(R.drawable.c_eight,8,R.drawable.c_four,4,R.drawable.c_six,6,R.drawable.count_pic3,6));
        al.add(new CountDemo(R.drawable.c_nine,9,R.drawable.c_six,6,R.drawable.c_eight,8,R.drawable.count_pic4,8));
        al.add(new CountDemo(R.drawable.c_nine,9,R.drawable.c_five,5,R.drawable.c_seven,7,R.drawable.count_pic5,9));
        al.add(new CountDemo(R.drawable.c_six,6,R.drawable.c_four,4,R.drawable.c_three,3,R.drawable.count_pic6,4));

    }

    private void retrieve(int i) {

        if(i<al.size()){
            cd=al.get(i);
        }

        id1=cd.getId1();
        id2=cd.getId2();
        id3=cd.getId3();
        bg=cd.getBg();
        v1=cd.getV1();
        v2=cd.getV2();
        v3=cd.getV3();
        vbg=cd.getVbg();

        im1.setImageResource(id1);
        im2.setImageResource(id2);
        im3.setImageResource(id3);
        imageView.setImageResource(bg);

        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        dialogChecker=new DialogChecker(this);
        switch (v.getId()){
            case R.id.btnback:
                intent=new Intent(this,DrawingTask.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
                break;

          /*  case R.id.cnext:
                /*if(flag==true){
                    intent=new Intent(this,Counting1.class);
                    startActivity(intent);
                    this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Select a Right Answer!!",Toast.LENGTH_SHORT).show();
                }
                break;*/

            case R.id.ibcount1:
                //flag=false;
                check(v1);
                break;

            case R.id.ibcount2:
                //flag=true;
                check(v2);
                //Log.i("value of v2:",""+v2);
                break;

            case R.id.ibcount3:
                //flag=false;
                check(v3);
                break;
        }
    }

    private void check(int v) {


        if(v==vbg){
            dialogChecker.showCorrect();
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
        else {
            dialogChecker.showInCorrect();
        }
    }


}
