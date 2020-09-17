package com.listview.shopthoitrang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.model.LoaiSP;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoaiSPadapter extends BaseAdapter {
    ArrayList<LoaiSP> listloaisp;
    Context context;

    public LoaiSPadapter(ArrayList<LoaiSP> listloaisp, Context context) {
        this.listloaisp = listloaisp;
        this.context = context;
    }
    @Override
    public int getCount() {
        return listloaisp.size();
    }

    @Override
    public Object getItem(int position) {
        return listloaisp.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txttenloaisp;
        ImageView imgloaisp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_dong,null,false);
            viewHolder.txttenloaisp=(TextView)convertView.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp=(ImageView)convertView.findViewById(R.id.imageview);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        //lay du lieu
        LoaiSP loaisp=(LoaiSP)getItem(position);
        viewHolder.txttenloaisp.setText(loaisp.getTensp());
        Picasso.with(context).load(loaisp.getImagesp())
                .into(viewHolder.imgloaisp);

        return convertView;
    }


}
