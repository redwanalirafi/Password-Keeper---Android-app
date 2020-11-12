package com.example.passwordkeeper11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class ChangePassword extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    EditText oldps, newps1,newps2;
    Button chngbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawerly);

        oldps=findViewById(R.id.oldpass);
        newps1=findViewById(R.id.newpass1);
        newps2=findViewById(R.id.newpass2);
        chngbtn=findViewById(R.id.changebtn);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.m_home) {
                    Intent intent = new Intent(ChangePassword.this,Home.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_about) {
                    Intent intent = new Intent(ChangePassword.this,AboutMe.class);
                    startActivity(intent);

                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_logout) {
                    Intent intent = new Intent(ChangePassword.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        chngbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old=oldps.getText().toString();
                String new1=newps1.getText().toString();
                String new2=newps2.getText().toString();

                SharedPreferences sh = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                String password=sh.getString("Passwordkey","12345");

                if(old!=null && new1!=null && new2!=null){
                    if(new1.equals(new2) && old.equals(password)){
                        //update password
                        SharedPreferences sharedPreferences = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("Passwordkey",new1);
                        editor.commit();

                        Toast.makeText(getApplicationContext(),"Password Changed!",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password didn't matched!"+password,Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect Input",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}