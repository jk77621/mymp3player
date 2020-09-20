package com.example.mymp3project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    private EditText edtID;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SOUND WAVE");

        edtID = findViewById(R.id.edtID);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnExit = findViewById(R.id.btnExit);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtID.getText().toString().equals("manager1") && edtPassword.getText().toString().equals("1234")) {
                    Intent intent = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "SOUND WAVE에 오신 것을 환영합니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "아이디, 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                finishAndRemoveTask();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}
