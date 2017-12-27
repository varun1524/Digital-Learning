package com.example.varun.signtuarecapture;


import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.*;


import com.example.varun.drawing.DrawingTask;
import com.example.varun.digitallearning.R;
import com.example.varun.signtuarecapture.views.ScribbleView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANAM SHAH on 18-02-2015.
 */


public class ScribbleActivity extends Activity {

    private ScribbleView scribble_view;

    public ImageButton mPen, mEraser,mGetSign, mNew, mColor,mback;
    public  LinearLayout l;
    public  Button mSaved;

    public MySQLiteHelper db;

    public List<String> list;
    public List<Image> str;
    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scribble_activity);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        scribble_view = (ScribbleView) findViewById(R.id.scribble_view);
        l=(LinearLayout)findViewById(R.id.l1);


        mGetSign = (ImageButton) findViewById(R.id.getsign);
        //mGetSign.setEnabled(false);
        mNew = (ImageButton) findViewById(R.id.cancel);
        mPen = (ImageButton) findViewById(R.id.pen);
        mEraser = (ImageButton) findViewById(R.id.erase);
        mColor = (ImageButton) findViewById(R.id.clr);
        mSaved=(Button)findViewById(R.id.saveimage);
        mback=(ImageButton)findViewById(R.id.iv_back);

        db = new MySQLiteHelper(ScribbleActivity.this);
        //db.deleteAll();

        list=new ArrayList<String>();

        //showSpinner();


        mPen.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scribble_view.setPen();

            }
        });

        mEraser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                scribble_view.setEraser();


            }
        });

        mNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scribble_view.newScrible();
            }
        });

        mColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ColorPickerDialog(ScribbleActivity.this, new ColorPickerDialog.OnColorChangedListener() {

                    public void colorChanged(int color) {
                        // TODO Auto-generated method stub
                        scribble_view.setPenColor(color);
                        scribble_view.setPen();
                        //mColor1 = color;
                    }
                }, 0xFFFF0000).show();
            }

        });

        mGetSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ScribbleView sv = new ScribbleView(ScribbleActivity.this);

                String path = sv.save(scribble_view);

                //  System.out.println(path + "activity");


                String message = "Image successfully saved...";
                Toast toast = Toast.makeText(ScribbleActivity.this, message, Toast.LENGTH_SHORT);
                toast.show();


                db.addImage(new Image(path));
                list.add(path);

                //showSpinnerItems();
                //db.getAllImages();

                // check();


            }
        });
        mSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileDlg();
            }
        });

        mback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(ScribbleActivity.this, DrawingTask.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                finish();
            }
        });

    }

    public void openFileDlg(){


        final ArrayList<Image> list=db.getAllImages();
        String fileList[]=new String[list.size()];
        for (int  i=0;i<list.size();i++){


            fileList[i]=list.get(i).getImgName();
            System.out.println("TESTING...");
            Log.i("TEST:",fileList[i]);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Make a choice from the list:");
        builder.setCancelable(false);
        builder.setInverseBackgroundForced(true);

        // builder.setAdapter()

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                ScribbleActivity.this,
                android.R.layout.select_dialog_singlechoice);



        builder.setItems(fileList, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                //Toast.makeText(getApplicationContext(), "Selection: " + item, Toast.LENGTH_SHORT).show();

                String text = list.get(item).getImgName();
                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyApp/Images/" + text;

                File imgFile = new File(path);
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    Bitmap mutableBitmap = myBitmap.copy(Bitmap.Config.ARGB_8888, true);
                    scribble_view.loadScrible(mutableBitmap);

                } else {
                    System.out.println("img doesn't exist..");
                }

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    public void showSpinnerItems()
    {
        int i=0;

        //  str =db.getAllImages();

        for (i = 0; i < str.size(); i++) {
            Image img = str.get(i);

            String path = img.getImgName();
            // System.out.println(path + i);

            list.add(path);
        }


        //  System.out.println("" + i);


    }

    public void showSpinner()
    {
        //db=new MySQLiteHelper(ScribbleActivity.this);
        //db.addImage(new Image("My.jpeg"));


        showSpinnerItems();

        // l=(LinearLayout)findViewById(R.id.ll2);
        final Spinner sp=new Spinner(ScribbleActivity.this);
        final ArrayAdapter<String> adp= new ArrayAdapter<String>(ScribbleActivity.this,
                android.R.layout.simple_list_item_1,list);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp.setAdapter(adp);
        l.addView(sp);
        //System.out.println("in show spinner");

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }

}




