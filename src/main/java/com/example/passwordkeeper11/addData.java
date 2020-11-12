package com.example.passwordkeeper11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class addData extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    EditText etemail, etpass;
    Button addbtn;
    TextView welcometv;
    DBhelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawerly);

        etemail = findViewById(R.id.emailid);
        etpass = findViewById(R.id.passid);
        addbtn = findViewById(R.id.addbtn);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.m_home) {
                    Intent intent = new Intent(addData.this,Home.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_about) {
                    Intent intent = new Intent(addData.this,AboutMe.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_logout) {
                    Intent intent = new Intent(addData.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });



        mydb = new DBhelper(this);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user, pass;
                user = etemail.getText().toString();
                pass = etpass.getText().toString();
                if (user != null && pass != null) {
                    boolean isInserted = mydb.insertData(user, pass);
                    if (isInserted == true) {
                        etemail.setText("");
                        etpass.setText("");
                        Toast.makeText(addData.this, "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(addData.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

    }

}