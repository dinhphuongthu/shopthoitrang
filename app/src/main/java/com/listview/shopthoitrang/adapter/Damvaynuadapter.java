package com.listview.shopthoitrang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Damvaynuadapter extends BaseAdapter {
    Context context;
    final ArrayList<SanPham> arrayList;
    public Damvaynuadapter(Context context, ArrayList<SanPham> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
     public class ViewHolder{
        public TextView txttensp,txtgiasp,txtmotasp;
        public ImageView imghinhsp;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_dongdamvaynu,null);
            viewHolder.txttensp=(TextView)convertView.findViewById(R.id.txttensp);
            viewHolder.txtgiasp=(TextView)convertView.findViewById(R.id.txtgiasp);
            viewHolder.imghinhsp=(ImageView)convertView.findViewById(R.id.imgdamvaynu);
            viewHolder.txtmotasp=(TextView)convertView.findViewById(R.id.txtmotasp);
            convertView.setTag(viewHolder);

        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        SanPham sp=(SanPham)getItem(position);
        viewHolder.txttensp.setText(sp.getName());

        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.txtgiasp.setText("Giá:"+""+decimalFormat.format(sp.getPrice())+"Đ");

        viewHolder.txtmotasp.setMaxLines(2);
        viewHolder.txtmotasp.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasp.setText(sp.getIntro());
        Picasso.with(context).load(sp.getImage()).resize(150,100).centerCrop().into(viewHolder.imghinhsp);
        return convertView;
    }
}
