package com.example.varun.digitallearning;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.AdapterView.OnItemClickListener;

import com.example.varun.adapters.UserListAdapter;
import com.example.varun.drawing.DrawingTask;

import java.util.ArrayList;


/**
 * Created by PANAM SHAH on 13-04-2015.
 */
public class ListUser extends Activity {
    ListView user_list;
    UserListAdapter adapter;
    ArrayList<User> users=new ArrayList<User>();
    public static String logged_user=null;
    Intent intent;
    ImageButton btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //UserDatabase ub=new UserDatabase(this);
        //ub.deleteAll();
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        btnadd=(ImageButton)findViewById(R.id.btnadduser);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(ListUser.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        user_list=(ListView)findViewById(R.id.user_list);
        adapter=new UserListAdapter(this,users);
        user_list.setAdapter(adapter);

        loadData();

        user_list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Cursor cursor=(Cursor)user_list.getItemAtPosition(position);
                //String s=cursor.getString(cursor.getColumnIndexOrThrow("id"));


                logged_user=users.get(position).getName();

                String pass=users.get(position).getPass();
                if(pass!=null){
                    intent=new Intent(ListUser.this,CheckPassword.class);
                    intent.putExtra("pass",pass);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
                else{
                    intent=new Intent(ListUser.this,DrawingTask.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    finish();
                }
                //Log.i("selected:",s);
            }

        });

    }

   /* @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // do something with the data
        String item = (String) getListAdapter().getItem(position);
        Log.i("selected",item);
    }*/

    public void loadData()
    {
        try{
            UserDatabase db=new UserDatabase(this);
            users.clear();
            users.addAll(db.getAllUsers());

            adapter.notifyDataSetChanged();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }



}
