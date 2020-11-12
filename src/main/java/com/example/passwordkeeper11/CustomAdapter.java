package com.example.passwordkeeper11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<String> email,pass;
    public CustomAdapter(Context context, ArrayList<String> email, ArrayList<String> pass){
        this.context=context;
        this.email=email;
        this.pass=pass;
    }
    @Override
    public int getCount() {
        return email.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.simple_view,viewGroup,false);
        }
        TextView mailtv=view.findViewById(R.id.listemail);
        TextView passtv=view.findViewById(R.id.listpass);

        mailtv.setText(email.get(i));
        passtv.setText(pass.get(i));
        return view;
    }
}
