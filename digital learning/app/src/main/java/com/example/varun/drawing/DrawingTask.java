package com.example.varun.drawing;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.varun.client.DialogMessanger;
import com.example.varun.connectdots.ConnectDots;
import com.example.varun.count.Counting;
import com.example.varun.fillcolor.DrawColor;
import com.example.varun.digitallearning.ListUser;
import com.example.varun.digitallearning.R;
import com.example.varun.matchcolor.MatchColor;
import com.example.varun.matchshapes.MatchShapes;

import com.example.varun.signtuarecapture.ScribbleActivity;


public class DrawingTask extends Activity implements View.OnClickListener{

    ImageButton btn1,btn2,btn3,btn4,btn5,btn6;
    ImageButton imhome,immsg;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        btn1=(ImageButton)findViewById(R.id.btn1);
        btn2=(ImageButton)findViewById(R.id.btn2);
        btn3=(ImageButton)findViewById(R.id.btn3);
        btn4=(ImageButton)findViewById(R.id.btn4);
        btn5=(ImageButton)findViewById(R.id.btn5);
        btn6=(ImageButton)findViewById(R.id.btn6);
        imhome=(ImageButton)findViewById(R.id.btnhome);
        immsg=(ImageButton)findViewById(R.id.btnclientmsg);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);

        imhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DrawingTask.this,ListUser.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        });

        immsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent = new Intent(this,SendMessage.class);
                DialogMessanger dialogMessanger=new DialogMessanger(DrawingTask.this);
                dialogMessanger.showDialog();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up ImageButton, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn1:
                intent = new Intent(this, DrawColor.class);
                break;

            case R.id.btn2:
                intent = new Intent(this,ConnectDots.class);
                break;

            case R.id.btn3:
                intent = new Intent(this,Counting.class);
                break;

            case R.id.btn4:
                intent = new Intent(this,MatchShapes.class);
                break;
            case R.id.btn5:
                intent = new Intent(this,MatchColor.class);
                break;
            case R.id.btn6:
                intent = new Intent(this,ScribbleActivity.class);
                break;

        }

        startActivity(intent);
        this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        finish();
    }


}
