package com.example.varun.digitallearning;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends Activity implements View.OnClickListener{


    EditText etname,etage,etstd;
    Button btNext,btCancel;
    Intent intent;
    //CheckBox cb;
    private String name,age,std;
    ImageButton ib;
    View viewImage;
    BitmapDrawable bdrawable;
    Bitmap thumbnail,bitmap;
    public UserDatabase db;

    // dbPath- absolute path of profile pictures, stored in database.
    public String dbPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        setContentView(R.layout.activity_main);

        etname=(EditText)findViewById(R.id.etname);
        etage=(EditText)findViewById(R.id.etage);
        etstd=(EditText)findViewById(R.id.etstd);

        etname.setFilters(new InputFilter[] {
                new InputFilter() {
                    public CharSequence filter(CharSequence src, int start,
                                               int end, Spanned dst, int dstart, int dend) {
                        if(src.equals("")){ // for backspace
                            return src;
                        }
                        if(src.toString().matches("[a-zA-Z ]+")){
                            return src;
                        }
                        return "";
                    }
                }
        });

        etage.setInputType(InputType.TYPE_CLASS_NUMBER);
        etstd.setInputType(InputType.TYPE_CLASS_NUMBER);
        etname.setInputType(InputType.TYPE_CLASS_TEXT);

        etname.setGravity(Gravity.TOP | Gravity.LEFT);
        etage.setGravity(Gravity.TOP | Gravity.LEFT);
        etstd.setGravity(Gravity.TOP | Gravity.LEFT);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //cb=(CheckBox)findViewById(R.id.cb);

        btNext=(Button)findViewById(R.id.nextbtn);
        btCancel=(Button)findViewById(R.id.cancelbtn);
        ib=(ImageButton)findViewById(R.id.ibsun);

        viewImage=new View(this);

        //Log.i("value",name);

        db=new UserDatabase(this);

        btNext.setOnClickListener(this);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etname.setText(null);
                etage.setText(null);
                etstd.setText(null);
                //cb.setChecked(false);
                intent = new Intent(MainActivity.this,ListUser.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();

            }
        });
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name=etname.getText().toString();
        age=etage.getText().toString();
        std=etstd.getText().toString();




        if(name.length()<2 || name.equals(null))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Name !", Toast.LENGTH_LONG).show();
        }
        else if((Integer.parseInt(age)<3) || (age.equals(null)) || (Integer.parseInt(age)>10))
        {
            Toast.makeText(getApplicationContext(), "Please enter Appropriate Age !",Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(std)>4){
            Toast.makeText(getApplicationContext(), "Please enter Appropriate Standard!",Toast.LENGTH_LONG).show();
        }
        else {
            switch (v.getId()) {
                case R.id.nextbtn:
                    User user=new User(name,age,std,null,dbPath);
                    db.addUser(user);
                    intent = new Intent(this, EnterPasscode.class);
                    startActivity(intent);
                    this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                    finish();
                    break;




                case R.id.ibsun:
                    selectImage();
                    break;

            }
        }
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");



                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);

                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    public void getUser()
    {
        intent = new Intent(this, ListUser.class);
        startActivity(intent);
        db.getAllUsers();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    //viewImage.setImageBitmap(bitmap);

                    //ib.setImageDrawable(bitmap);

                    dbPath="" + f.getAbsolutePath();

                    bdrawable = new BitmapDrawable(bitmap);
                    ib.setImageDrawable(bdrawable);

                    String path = Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";

                    //String path=Environment.getExternalStorageDirectory().getAbsolutePath()+"/UserPic/pic/";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");



                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.w("path of image from gallery....******************....", picturePath + "");

                dbPath="" + picturePath;

                bdrawable = new BitmapDrawable(thumbnail);
                ib.setImageDrawable(bdrawable);

            }
        }


    }


}