package com.listview.shopthoitrang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.model.GioHang;
import com.listview.shopthoitrang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietDamVayNu extends AppCompatActivity {
    public static final String TAG = ChiTietDamVayNu.class.getSimpleName();
    Toolbar toolbarchitiet;
    ImageView imgChitiet;
    TextView txtten,txtgia,txtmota;
    Spinner spinner;
    Button btndatmua;
    int id;
    int Id=0;
    String Name="";
    long Giachitiet=0;
    String Image="";
    String Intro="";
    int Feature=0;
    int Catid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_dam_vay_nu);
        init();
        ActionToolBar();
        GetInformation();
        CatchEventSpinner();
        CatchEventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(),giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInformation() {

        SanPham sp = (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        Id=sp.getId();
        Name=sp.getName();
        Giachitiet=sp.getPrice();
        Image=sp.getImage();
        Intro=sp.getIntro();
        Feature=sp.getFeature();
        Catid=sp.getCatid();
        txtten.setText(Name);
        DecimalFormat decimalFormat = new DecimalFormat("###,###.###");
        txtgia.setText("giá:"+decimalFormat.format(Giachitiet)+""+"Đ");
        txtmota.setText(Intro);
        Picasso.with(getApplicationContext()).load(Image).resize(150,100).centerCrop()
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChitiet);
    }

    private void CatchEventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.listgiohang1.size()>0){

                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exists=false;

                    for(int i=0;i<MainActivity.listgiohang1.size();i++){
                        if(MainActivity.listgiohang1.get(i).getIdsp()== Id) {
                            MainActivity.listgiohang1.get(i).setSoluongsp(MainActivity.listgiohang1.get(i).getSoluongsp() + sl);
                            if (MainActivity.listgiohang1.get(i).getSoluongsp() >= 10) {
                                MainActivity.listgiohang1.get(i).setSoluongsp(10);
                            }
                            MainActivity.listgiohang1.get(i).setGiasp(Giachitiet * MainActivity.listgiohang1.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if(exists==false){
                        int soluong =Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi=soluong*Giachitiet;
                        MainActivity.listgiohang1.add(new GioHang(Id,Name,giamoi,Image,soluong));
                    }

                }else{
                    int soluong =Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi=soluong * Giachitiet;
                    MainActivity.listgiohang1.add(new GioHang(Id,Name,giamoi,Image,soluong));
                }
                Intent intent = new Intent(getApplicationContext(),giohang.class);
                startActivity(intent);

            }
        });
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void init() {

        toolbarchitiet=(Toolbar)findViewById(R.id.toolbarchitietsanpham);
        imgChitiet=(ImageView)findViewById(R.id.imgchitietsp);
        txtten=(TextView)findViewById(R.id.tvtensp);
        txtgia=(TextView)findViewById(R.id.tvgiasp);
        txtmota=(TextView)findViewById(R.id.tvmotachitietsp);
        spinner=(Spinner)findViewById(R.id.spinner);
        btndatmua=(Button)findViewById(R.id.btnthemgiohang);
    }

}
