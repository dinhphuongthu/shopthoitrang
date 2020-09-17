package com.listview.shopthoitrang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.activity.MainActivity;
import com.listview.shopthoitrang.model.GioHang;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> arrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> arrayList) {
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
final public class ViewHolder{
    public TextView txttenmonhang,txtgiamonhang;
    public ImageView imgmonhang;
    public Button btnminus,btnvalue,btnplus;

}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_giohang,null,false);
            viewHolder.txttenmonhang=(TextView)convertView.findViewById(R.id.tvtenmonhang);
            viewHolder.txtgiamonhang=(TextView)convertView.findViewById(R.id.tvgiamonhang);
            viewHolder.imgmonhang=(ImageView)convertView.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus=(Button)convertView.findViewById(R.id.buttonminus);
            viewHolder.btnvalue=(Button)convertView.findViewById(R.id.buttonvalue);
            viewHolder.btnplus=(Button)convertView.findViewById(R.id.buttonplus);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
        final GioHang giohang=(GioHang)getItem(position);
        viewHolder.txttenmonhang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        viewHolder.txtgiamonhang.setText(""+decimalFormat.format(giohang.getGiasp())+"Đ");
        Picasso.with(context).load(giohang.getHinhsp()).resize(150,100).centerCrop().into(viewHolder.imgmonhang);
        viewHolder.btnvalue.setText(""+giohang.getSoluongsp());
        final int sl=Integer.parseInt(viewHolder.btnvalue.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl<=1) {
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }

        final ViewHolder finalViewHolder = viewHolder;
        //bat su kien btnplus
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalue.getText().toString())+1;
                int slht= MainActivity.listgiohang1.get(position).getSoluongsp();
                long giaht=MainActivity.listgiohang1.get(position).getGiasp();
                MainActivity.listgiohang1.get(position).setSoluongsp(slmoinhat);
                long giamoinhat= (giaht*slmoinhat)/slht;
                MainActivity.listgiohang1.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
                finalViewHolder.txtgiamonhang.setText(""+decimalFormat.format(giamoinhat)+"Đ");
                com.listview.shopthoitrang.activity.giohang.EventUntil();
                if(slmoinhat>9){
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
            }
        });
        //bat su kien btnminus
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat=Integer.parseInt(finalViewHolder.btnvalue.getText().toString())-1;
                int slht= MainActivity.listgiohang1.get(position).getSoluongsp();
                long giaht=MainActivity.listgiohang1.get(position).getGiasp();
                MainActivity.listgiohang1.get(position).setSoluongsp(slmoinhat);
                long giamoinhat= (giaht*slmoinhat)/slht;
                MainActivity.listgiohang1.get(position).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
                finalViewHolder.txtgiamonhang.setText(""+decimalFormat.format(giamoinhat)+"Đ");
                com.listview.shopthoitrang.activity.giohang.EventUntil();
                if(slmoinhat<2){
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return convertView;
    }
}
