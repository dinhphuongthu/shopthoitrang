package com.listview.shopthoitrang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.model.Account;
import com.listview.shopthoitrang.util.Server;
import com.listview.shopthoitrang.activity.ResultLogin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TaiKhoan extends AppCompatActivity {

    public static final String TAG = TaiKhoan.class.getSimpleName();
    private EditText edtUserName;
    private EditText edtPassWord;
    private Button btnLogin;
    private Button btnRegister;
    private ProgressDialog pDialog;
    /**
     * URL : URL_LOGIN
     * param : KEY_USERNAME KEY_PASSWORD
     */
    public static final String URL_LOGIN = Server.urllogin;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        //ActionToolBar();
        addControl();
        addEvent();
    }
    private void addEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get value input
                String username = edtUserName.getText().toString().trim();
                String password = edtPassWord.getText().toString().trim();
                // Call method
                loginAccount(username, password);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaiKhoan.this, Register.class);
                startActivity(intent);
            }
        });
    }
    private void addControl() {
        edtUserName = (EditText) findViewById(R.id.editUsername);
        edtPassWord = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Đang đăng nhập...");
        pDialog.setCanceledOnTouchOutside(false);
    }
    public void loginAccount(final String username, final String password) {

        if (checkEditText(edtUserName) && checkEditText(edtPassWord)) {
            pDialog.show();
            StringRequest requestLogin = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response);
                            String message = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (jsonObject.getInt("success") == 1) {
                                    Account account = new Account();
                                    account.setUserName(jsonObject.getString("user_name"));
                                    account.setEmail(jsonObject.getString("email"));
                                    message = jsonObject.getString("message");
                                    Toast.makeText(TaiKhoan.this, message, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(TaiKhoan.this, ResultLogin.class);
                                    intent.putExtra("login", account);
                                    startActivity(intent);
                                } else {
                                    message = jsonObject.getString("message");
                                    Toast.makeText(TaiKhoan.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d(TAG, "Error: " + error.getMessage());
                            pDialog.dismiss();
                        }
                    }) {
                /**
                 * set paramater
                 * */
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put(KEY_USERNAME, username);
                    params.put(KEY_PASSWORD, password);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(requestLogin);
        }
    }
    private boolean checkEditText(EditText editText) {
        if (editText.getText().toString().trim().length() > 0)
            return true;
        else {
            editText.setError("Vui lòng nhập dữ liệu!");
        }
        return false;
    }
}



