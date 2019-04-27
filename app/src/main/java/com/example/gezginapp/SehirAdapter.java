package com.example.gezginapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SehirAdapter extends BaseAdapter {

    Context c;
    ArrayList<Sehir> list;

    public SehirAdapter(Context c, ArrayList<Sehir> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = null;
        LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            row = layoutInflater.inflate(R.layout.sehir_item,parent,false);
        }
        else
        {
            row=convertView;
        }
        ImageView img = row.findViewById(R.id.sehirfoto);
        TextView isim = row.findViewById(R.id.sehirisim);
        TextView bilgi = row.findViewById(R.id.sehirbilgi);
        img.setBackgroundResource(list.get(position).getFotografID());
        isim.setText(list.get(position).getIsim());
        bilgi.setText(list.get(position).getBilgi());
        return row;
    }
}
