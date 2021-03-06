package com.listview.shopthoitrang.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.adapter.Damvaynuadapter;
import com.listview.shopthoitrang.model.SanPham;
import com.listview.shopthoitrang.util.CheckConnection;
import com.listview.shopthoitrang.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Damvaynu extends AppCompatActivity {
   ListView listviewdamvaynu;

   private ArrayList<SanPham> listsanpham;
   private Damvaynuadapter listadapter;
    int id;
    Toolbar toolbarlistsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damvaynu);
        init();
        ActionToolBar();
/*
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {

           // getdata();
        }else{
            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại Internet!");
            finish();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.giohang,menu);
        return true;
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


    private void init() {
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            id=bundle.getInt("idloaisanpham");
            Toast.makeText(Damvaynu.this,id+"",Toast.LENGTH_SHORT).show();
        }
        listviewdamvaynu=(ListView)findViewById(R.id.listviewdamvaynu);
        listsanpham=new ArrayList<>();

        getdata();
        listadapter=new Damvaynuadapter(Damvaynu.this,listsanpham);
        listviewdamvaynu.setAdapter(listadapter);
        listviewdamvaynu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ChiTietDamVayNu.class);
                intent.putExtra("thongtinsanpham",listsanpham.get(position));
                startActivity(intent);
            }
        });
        toolbarlistsanpham=(Toolbar)findViewById(R.id.toolbarlistsanpham);
    }
    private void ActionToolBar() {
        setSupportActionBar(toolbarlistsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlistsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void getdata() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        //public static String urlSanpham="http://"+localhost+"/shop/getsanpham.php?page=";
        String duongdan= Server.urlSanpham+""+id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(duongdan, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int Id = 0;
                String Name = "";
                int Price = 0;
                String Image = "";
                String Intro = "";
                int Feature = 0;
                int Catid = 0;
                //nếu có json
                if (response != null) {

                    for(int i=0;i<response.length();i++){
                        //JSONObject jsonObject = null;
                        try {
                            JSONObject  jsonObject = response.getJSONObject(i);
                            Id=jsonObject.getInt("id");
                            Name=jsonObject.getString("name");
                            Price=jsonObject.getInt("price");
                            Image=jsonObject.getString("image");
                            Intro=jsonObject.getString("intro");
                            Feature=jsonObject.getInt("feature");
                            Catid=jsonObject.getInt("catid");
                            listsanpham.add(new SanPham(Id,Name, (long) Price,Image,Intro,Feature,Catid));
                            listadapter.notifyDataSetChanged();
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
}
