package com.kanchan.foodmandu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaDataSource;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kanchan.foodmandu.bll.LoginBLL;
import com.kanchan.foodmandu.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {
    private Button btnlogin;
    private EditText etusername, etpassword;
    private TextView tvsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin=findViewById(R.id.btnLogin);
        etusername=findViewById(R.id.etUsername);
        etpassword=findViewById(R.id.etPassword);
        tvsignup=findViewById(R.id.tvSignup);

        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "open registation page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                login();
            }
        });

    }
    private void login(){
        String username=etusername.getText().toString();
        String password=etpassword.getText().toString();

        LoginBLL loginBLL=new LoginBLL();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            Intent intent = new Intent(LoginActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
//          Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etusername.requestFocus();
        }

    }

}
