package com.wonnur.bajuhijau;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wonnur.bajuhijau.ui.UserInfoSP;

public class LoginActivity extends AppCompatActivity {
    EditText txtuserid;
    EditText txtPassword;
    Button btnsignin;

    private UserInfoSP userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userinfo = UserInfoSP.getInstance(this);

        txtuserid = findViewById(R.id.txtuserid);
        txtPassword = findViewById(R.id.txtPassword);

        //retrieve login data

        txtuserid.setText(userinfo.getUserID());
        txtPassword.setText(userinfo.getUserPwd());


        btnsignin = findViewById(R.id.btnsignin);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userid = txtuserid.getText().toString();
                String userpwd = txtPassword.getText().toString();

                if (userid.equalsIgnoreCase("user10") && userpwd.equals("1234567890")) {

                    //if success then save the login data
                    userinfo.setLoginSuccess(userid,userpwd);

                    Toast.makeText(getApplicationContext(), "success login", Toast.LENGTH_LONG).show();

                    //after success login then call main page

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);


                    //close login interface

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "fail login", Toast.LENGTH_LONG).show();

                }
            }
        });





    }

    @Override
    public void onBackPressed(){
        try{

        }catch(Exception e){

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage("Confirm exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}