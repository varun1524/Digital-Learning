package com.example.varun.digitallearning;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by varun on 11/02/15.
 */
public class ConfirmPasscode extends Activity implements View.OnClickListener {
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12;
    ImageView iv1,iv2,iv3,iv4;
    public int count=0;
    private StringBuilder sb=new StringBuilder(4);
    Button btNext,btSkip,btCancel;
    Intent intent;
    String cpswd,pswd;
    int c=0;

    public UserDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reenter_passcode);

        ActionBar actionBar = getActionBar();
        actionBar.hide();

        bt1=(Button)findViewById(R.id.btn1);
        bt2=(Button)findViewById(R.id.btn2);
        bt3=(Button)findViewById(R.id.btn3);
        bt4=(Button)findViewById(R.id.btn4);
        bt5=(Button)findViewById(R.id.btn5);
        bt6=(Button)findViewById(R.id.btn6);
        bt7=(Button)findViewById(R.id.btn7);
        bt8=(Button)findViewById(R.id.btn8);
        bt9=(Button)findViewById(R.id.btn9);
        bt10=(Button)findViewById(R.id.btn10);
        bt11=(Button)findViewById(R.id.btn11);
        bt12=(Button)findViewById(R.id.btn12);
        btNext=(Button)findViewById(R.id.nextbtn);
        btCancel=(Button)findViewById(R.id.cancelbtn);



        iv1=(ImageView)findViewById(R.id.imageView1);
        iv2=(ImageView)findViewById(R.id.imageView2);
        iv3=(ImageView)findViewById(R.id.imageView3);
        iv4=(ImageView)findViewById(R.id.imageView4);

        db=new UserDatabase(ConfirmPasscode.this);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        bt9.setOnClickListener(this);
        bt10.setOnClickListener(this);
        bt11.setOnClickListener(this);
        bt12.setOnClickListener(this);
        btNext.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        //Log.i("count",":"+count);
        if (sb.length() < 4) {
            switch (v.getId()) {
                case R.id.btn1:
                    count++;
                    sb.append(1);
                    break;
                case R.id.btn2:
                    sb.append(2);
                    count++;
                    break;
                case R.id.btn3:
                    sb.append(3);
                    count++;
                    break;
                case R.id.btn4:
                    sb.append(4);
                    count++;
                    break;
                case R.id.btn5:
                    sb.append(5);
                    count++;
                    break;
                case R.id.btn6:
                    sb.append(6);
                    count++;
                    break;
                case R.id.btn7:
                    sb.append(7);
                    count++;
                    break;
                case R.id.btn8:
                    sb.append(8);
                    count++;
                    break;
                case R.id.btn9:
                    sb.append(9);
                    count++;
                    break;
                case R.id.btn10:

                    //count++;
                    break;
                case R.id.btn11:
                    sb.append(0);
                    count++;
                    break;


            }
        }

        switch (v.getId()) {
            case R.id.nextbtn:
                if (sb.length() == 4) {
                    cpswd = sb.toString();
                    Log.i("confirmed password: ", cpswd);
                    goNext();
                }
                break;

            case R.id.cancelbtn:
                goBack();
                break;
            case R.id.btn12:
                //Log.i("length and confirm password before:", sb.length() + "\n" + sb.toString());
                if (sb.length() > 0 && sb.length() <= 4) {
                    int i = sb.length() - 1;
                    try {
                        sb = sb.deleteCharAt(i);
                        remove(i + 1);
                    } catch (StringIndexOutOfBoundsException e) {
                        e.printStackTrace();
                    }

                    // Log.i("length and confirm password after:", sb.length() + sb.toString());
                } else {
                    //do nothing
                }
                break;
        }



        Log.i("length of string", ":" + sb.length());
        Log.i("print", sb.toString());

        //User u1=new User("");





        switch (sb.length()) {
            case 1:
                iv1.setImageResource(R.drawable.filled_circle);
                break;
            case 2:
                iv2.setImageResource(R.drawable.filled_circle);
                break;
            case 3:
                iv3.setImageResource(R.drawable.filled_circle);
                break;
            case 4:
                iv4.setImageResource(R.drawable.filled_circle);
                break;

        }


    }


    private void goNext() {
        pswd=getIntent().getStringExtra("password");
        //Log.i("In Confirm Password password:",pswd);
        //Log.i("In Confirm Password Confirm password:",cpswd);
        if(!pswd.equals(cpswd)){
            Toast.makeText(getApplicationContext(),"PassCode does not match",Toast.LENGTH_LONG).show();
            clear();
        }
        else {
            Log.i("in Confirm Passcode:","password match successfully");

            c=db.getProfilesCount();

            System.out.println("count:" + db.getProfilesCount());

            User u1= db.getUser(c);
            u1.setPass(sb.toString());
            db.updateUser(u1);
            db.getAllUsers();

            intent=new Intent(this,ListUser.class);
            startActivity(intent);
            this.overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
            finish();
        }
    }

    private void goBack() {
        intent=new Intent(this,EnterPasscode.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
        finish();
    }

    private void clear() {
        sb =sb.delete(0,4);
        count=0;
        iv1.setImageResource(R.drawable.circle);
        iv2.setImageResource(R.drawable.circle);
        iv3.setImageResource(R.drawable.circle);
        iv4.setImageResource(R.drawable.circle);
    }


    private void remove(int count) {
        switch (count){
            case 1:
                iv1.setImageResource(R.drawable.circle);
                break;
            case 2:
                iv2.setImageResource(R.drawable.circle);
                break;
            case 3:
                iv3.setImageResource(R.drawable.circle);
                break;
            case 4:
                iv4.setImageResource(R.drawable.circle);
                break;
        }
    }
}