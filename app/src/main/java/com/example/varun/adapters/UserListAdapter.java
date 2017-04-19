package com.example.varun.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.varun.digitallearning.R;
import com.example.varun.digitallearning.User;

import java.util.ArrayList;

/**
 * Created by PANAM SHAH on 15-04-2015.
 */
public class UserListAdapter extends ArrayAdapter<User> {
    ArrayList<User> list;
    Context context;

    static class Holder {

        TextView lbl1,lbl2;

    }

    public UserListAdapter(Context context, ArrayList<User> list) {
        super(context, R.layout.list_items);
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null)
        {
            holder=new Holder();
            convertView= LayoutInflater.from(context).inflate(R.layout.list_items,null);
            holder.lbl1=(TextView)convertView.findViewById(R.id.lbl_text);
           // holder.lbl2=(TextView)convertView.findViewById(R.id.lbl2_txt);
            convertView.setTag(holder);
        }
        else
        {
             holder = (Holder) convertView.getTag();
        }

        User user=list.get(position);

        holder.lbl1.setText(user.getName());
       // holder.lbl2.setText(user.getStd());

        return convertView;
    }
}
