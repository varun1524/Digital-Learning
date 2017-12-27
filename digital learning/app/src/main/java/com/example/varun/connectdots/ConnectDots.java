package com.example.varun.connectdots;




import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.varun.drawing.DrawingTask;
import com.example.varun.digitallearning.R;


public class ConnectDots extends Activity implements View.OnClickListener {
    ConnectViewOne connectViewOne;
    ConnectViewTwo connectViewTwo;
    ConnectViewThree connectViewThree;
    ConnectViewFour connectViewFour;
    ConnectViewFive connectViewFive;
    ConnectViewSix connectViewSix;
    Intent intent;
    ImageButton back;
    RelativeLayout dotslayout;
    ImageButton im1,im2,im3,im4,im5,im6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setContentView(R.layout.dots);

        back=(ImageButton)findViewById(R.id.btnback);
        im1=(ImageButton)findViewById(R.id.dots1);
        im2=(ImageButton)findViewById(R.id.dots2);
        im3=(ImageButton)findViewById(R.id.dots3);
        im4=(ImageButton)findViewById(R.id.dots4);
        im5=(ImageButton)findViewById(R.id.dots5);
        im6=(ImageButton)findViewById(R.id.dots6);

        dotslayout=(RelativeLayout)findViewById(R.id.rldots);
        //connectViewOne=new ConnectViewOne(this);
        //dotslayout.addView(connectViewOne);


        back.setOnClickListener(this);
        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);
        im4.setOnClickListener(this);
        im5.setOnClickListener(this);
        im6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dots1:
                connectViewOne=new ConnectViewOne(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewOne);
                break;
            case R.id.dots2:
                connectViewTwo=new ConnectViewTwo(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewTwo);
                break;
            case R.id.dots3:
                connectViewThree=new ConnectViewThree(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewThree);
                break;
            case R.id.dots4:
                connectViewFour=new ConnectViewFour(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewFour);
                break;
            case R.id.dots5:
                connectViewFive=new ConnectViewFive(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewFive);
                break;
            case R.id.dots6:
                connectViewSix=new ConnectViewSix(this);
                dotslayout.removeAllViews();
                dotslayout.addView(connectViewSix);
                break;
            case R.id.btnback:
                intent=new Intent(this,DrawingTask.class);
                startActivity(intent);
                this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
        }

    }
}