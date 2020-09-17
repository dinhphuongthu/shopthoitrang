package com.listview.shopthoitrang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.util.CheckConnection;
import com.listview.shopthoitrang.util.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Thongtinkhachhang extends AppCompatActivity {
    EditText edttenkh,edtsodt,edtemail,edtdiachi;
    Button btnxacnhan,btntrove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkhachhang);
        init();
        btntrove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.showToast_short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!");
        }

    }

    private void EventButton() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ten = edttenkh.getText().toString().trim();
                final String sdt = edtsodt.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                final String diachi = edtdiachi.getText().toString().trim();

                  if(ten.length()>0 && sdt.length()>0 && email.length()>0 && diachi.length()>0){
                      RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                      StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlDonHang, new Response.Listener<String>() {
                         @Override
                          public void onResponse(final String madonhang) {
                              Log.d("madonhang",madonhang);
                              //kiểm tra nếu response > 0 thì gửi dữ liệu lên cho server
                              if(Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.urlChiTietDonHang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.listgiohang1.clear();
                                            CheckConnection.showToast_short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
                                            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.showToast_short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
                                        }else{
                                            CheckConnection.showToast_short(getApplicationContext(),"Dữ liệu giỏ hàng của bạn đã bị lỗi");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i=0;i<MainActivity.listgiohang1.size();i++)
                                        {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.listgiohang1.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.listgiohang1.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.listgiohang1.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.listgiohang1.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            //đưa dữ liệu(jsonObject) vào jsonArray.
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                              }
                          }
                      }, new Response.ErrorListener() {
                          @Override
                          public void onErrorResponse(VolleyError error) {

                          }
                      }) {
                          @Override
                          protected Map<String, String> getParams() throws AuthFailureError {
                              HashMap<String,String> hashMap = new HashMap<String,String>();
                              hashMap.put("tenkhachhang",ten);
                              hashMap.put("sodienthoai",sdt);
                              hashMap.put("email",email);
                              hashMap.put("diachi",diachi);
                              return hashMap;
                          }
                      };
                      requestQueue.add(stringRequest);
                  }else{
                      CheckConnection.showToast_short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu nhập");
                  }


            }
        }
        );
    }

    private void init() {
        edttenkh=(EditText) findViewById(R.id.edttenkh);
        edtsodt=(EditText) findViewById(R.id.edtphonekh);
        edtemail=(EditText) findViewById(R.id.edtemailkh);
        edtdiachi=(EditText) findViewById(R.id.edtDIACHIkh);
        btnxacnhan=(Button) findViewById(R.id.btnxacnhan);
        btntrove=(Button) findViewById(R.id.btnTROVE);

    }
}
