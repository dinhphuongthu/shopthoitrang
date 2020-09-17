package com.listview.shopthoitrang.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.adapter.GioHangAdapter;
import com.listview.shopthoitrang.util.CheckConnection;

import java.text.DecimalFormat;

public class giohang extends AppCompatActivity {
    static TextView txttongtien;
    Toolbar toolbargiohang;
    ListView lvgiohang;
    TextView txtthongbao;
    Button btnthanhtoan,btntieptucmua;
    GioHangAdapter listadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        init();
        ActionToolBar();
        init();
        checkdata();
        EventUntil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listgiohang1.size()>0)
                {
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);
                }else{
                    CheckConnection.showToast_short(getApplicationContext(),"giỏ hàng của bạn chưa có sản phẩm để thanh toán");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(giohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm không");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       if(MainActivity.listgiohang1.size()<=0){
                           txtthongbao.setVisibility(View.VISIBLE);
                       }else{
                           MainActivity.listgiohang1.remove(position);
                           listadapter.notifyDataSetChanged();
                           EventUntil();
                           if(MainActivity.listgiohang1.size()<=0){
                               txtthongbao.setVisibility(View.VISIBLE);
                           }else{
                               MainActivity.listgiohang1.remove(position);
                               listadapter.notifyDataSetChanged();
                               EventUntil();
                           }
                       }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listadapter.notifyDataSetChanged();
                        EventUntil();

                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EventUntil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.listgiohang1.size();i++){
            tongtien+=MainActivity.listgiohang1.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        txttongtien.setText(""+decimalFormat.format(tongtien)+"Đ");
    }





    private void init() {
        toolbargiohang=(Toolbar)findViewById(R.id.toolbargiohang);
        txtthongbao=(TextView)findViewById(R.id.tvthongbao);
        lvgiohang=(ListView)findViewById(R.id.listviewgiohang);
        txttongtien=(TextView)findViewById(R.id.tvtongtien);
        btnthanhtoan=(Button)findViewById(R.id.btnthanhtoangiohang);
        btntieptucmua=(Button)findViewById(R.id.btntieptucmuahang);
        listadapter=new GioHangAdapter(getApplicationContext(),MainActivity.listgiohang1);
        lvgiohang.setAdapter(listadapter);
    }

    private void checkdata() {
        if(MainActivity.listgiohang1.size()<=0)
        {   listadapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }else{
            listadapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
