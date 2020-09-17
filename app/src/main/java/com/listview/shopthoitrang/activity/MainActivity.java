package com.listview.shopthoitrang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.adapter.LoaiSPadapter;
import com.listview.shopthoitrang.adapter.SanPhamadapter;
import com.listview.shopthoitrang.model.GioHang;
import com.listview.shopthoitrang.model.LoaiSP;
import com.listview.shopthoitrang.model.SanPham;
import com.listview.shopthoitrang.util.CheckConnection;
import com.listview.shopthoitrang.util.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SanPhamadapter.SelectedProduct{
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listViewmanhinhchinh,listviewsanpham;
    DrawerLayout drawerLayout;
    RecyclerView recyclerView;
    ArrayList<LoaiSP> listloaisp;
    LoaiSPadapter listloaispadapter;
    int id=0;
    String tenloaisp="";
    String hinhanhsp="";
    ArrayList<SanPham> listsanpham;
    SanPhamadapter listspadapter;
    public static ArrayList<GioHang> listgiohang1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            //ham bat su kien cho toolbar
            ActionBar();
            //ham bat su kien cho ViewFlipper chay quang cao
            ActionViewFlipper();
            getdulieuloaisp();
            getdulieusanphammoinhat();
            CatchOnItemListView();
        }
        else{
            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
     MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.giohang,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent=new Intent(getApplicationContext(),giohang.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,Damvaynu.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,AoNu.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,ChanVay.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,Quan.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,LienHe.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 6:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent=new Intent(MainActivity.this,Register.class);
                            intent.putExtra("idloaisanpham",listloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

            }
        });
    }

    private void getdulieusanphammoinhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlsanphammoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)//neu du lieu json ton tai
                {   int id1=0;
                    String name="";
                    long price=0;
                    String image="";
                    String intro="";
                    int feature=0;
                    int catid=0;

                    for(int i=0;i<response.length();i++)  {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id1=jsonObject.getInt("id");
                            name=jsonObject.getString("name");
                            price=jsonObject.getLong("price");
                            image=jsonObject.getString("image");
                            intro=jsonObject.getString("intro");
                            feature=jsonObject.getInt("feature");
                            catid=jsonObject.getInt("catid");
                            listsanpham.add(new SanPham(id1,name,price,image,intro,feature,catid));
                            listspadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getdulieuloaisp() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.urlloaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null)//neu du lieu json ton tai
                {
                    for(int i=0;i<response.length();i++)  {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id=jsonObject.getInt("id");
                            tenloaisp=jsonObject.getString("name");
                            hinhanhsp=jsonObject.getString("image");
                            listloaisp.add(new LoaiSP(id,tenloaisp,hinhanhsp));
                            listloaispadapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
        listloaisp.add(5,new LoaiSP(0,"Liên hệ","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRQBRW8EDnycdskgONzvnmOiEqjTRxaygjrXpy9O6SCGe2jPfCm&usqp=CAU"));
        listloaisp.add(6,new LoaiSP(0,"Tài khoản","https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQ0La2HZleKC9KaMgJatGhQgxkWyIoK4IQnyxlz0z85s4egHsdf&usqp=CAU"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void ActionViewFlipper(){
            ArrayList<String> mangquangcao=new ArrayList<>();//khai bao va cap phat bo nho
            mangquangcao.add("https://g9moza.com.vn/wp-content/uploads/2020/03/d%E1%BB%8Dc-trong.jpg");
            mangquangcao.add("https://g9moza.com.vn/wp-content/uploads/2020/02/bia-anh-%C4%91%E1%BA%A7u.jpg");
            mangquangcao.add("https://g9moza.com.vn/wp-content/uploads/2019/09/bia.jpg");
            // mangquangcao.add("https://g9moza.com.vn/wp-content/uploads/2020/04/35.2.jpg");
            //  mangquangcao.add("https://g9moza.com.vn/wp-content/uploads/2020/04/37.3.jpg");
            for(int i=0;i<mangquangcao.size();i++)
            {
                ImageView imageView = new ImageView(getApplicationContext());
                //Load ảnh từ url trong mangquangcao về imageview
                Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
                //chỉnh kích thước để imageview vừa đủ với ViewFlipper
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                viewFlipper.addView(imageView);
            }
            //bắt sự kiện cho viewflipper chạy.
            viewFlipper.setFlipInterval(5000);
            viewFlipper.setAutoStart(true);
            Animation animation_slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
            Animation animation_slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
            viewFlipper.setAnimation(animation_slide_in);
            viewFlipper.setAnimation(animation_slide_out);
        }
    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void init()
    {   toolbar=(Toolbar)findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=(ViewFlipper) findViewById(R.id.viewfliper);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        navigationView=(NavigationView)findViewById(R.id.navigationview);
        listViewmanhinhchinh=(ListView)findViewById(R.id.listviewmanhinhchinh);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        listloaisp=new ArrayList<>();
        listloaisp.add(0,new LoaiSP(0,"Trang Chính","https://imageog.flaticon.com/icons/png/512/25/25694.png?size=1200x630f&pad=10,10,10,10&ext=png&bg=FFFFFFFF"));
        listloaispadapter=new LoaiSPadapter(listloaisp,getApplicationContext());
        listViewmanhinhchinh.setAdapter(listloaispadapter);
        listsanpham=new ArrayList<>();
        listspadapter=new SanPhamadapter(listsanpham,this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setAdapter(listspadapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        if(listgiohang1!=null){
            Toast.makeText(MainActivity.this,"co san pham trong gio hang:"+" "+listgiohang1.size(),Toast.LENGTH_SHORT).show();
        }else{
            listgiohang1=new ArrayList<>();
        }
    }
    @Override
    public void selectedProduct(SanPham sanpham){
        startActivity(new Intent(MainActivity.this,ChitietSP.class).putExtra("data",sanpham));
    }


}
