package com.listview.shopthoitrang.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.listview.shopthoitrang.R;
import com.listview.shopthoitrang.model.Account;
import com.listview.shopthoitrang.model.SharedPrefManager;

public class ResultLogin extends AppCompatActivity {
    private TextView txtUserName;
    private TextView txtEmail;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_login2);
        Intent intent = getIntent();
        account = new Account();
        account = (Account) intent.getSerializableExtra("login");
        addControl();
    }

    private void addControl() {
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        /**Set value*/
        txtUserName.setText(account.getUserName());
        txtEmail.setText(account.getEmail());
    }
}



