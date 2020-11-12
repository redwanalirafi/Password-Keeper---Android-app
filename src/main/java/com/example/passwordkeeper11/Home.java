package com.example.passwordkeeper11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    AlertDialog.Builder alart;
    DBhelper mydb;

    ImageView addData,viewData,clearData,changePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addData=findViewById(R.id.adddata);
        viewData=findViewById(R.id.viewdata);
        clearData=findViewById(R.id.clandata);
        changePassword=findViewById(R.id.changeps);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav=findViewById(R.id.navmenu);
        drawerLayout=findViewById(R.id.drawerly);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.m_home){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(menuItem.getItemId()==R.id.m_about){
                    Intent intent = new Intent(Home.this,AboutMe.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(menuItem.getItemId()==R.id.m_logout){
                    Intent intent = new Intent(Home.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        //Img Btns


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,addData.class);
                startActivity(intent);
            }
        });
        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ViewData.class);
                startActivity(intent);
            }
        });
        mydb=new DBhelper(this);
        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alart=new AlertDialog.Builder(Home.this);
                alart.setTitle("Are you sure?");
                alart.setMessage("Are you sure want to clear all your data?");
                alart.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mydb.cleardata();
                        Toast.makeText(getApplicationContext(),"Removed",Toast.LENGTH_SHORT).show();
                    }
                });
                alart.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog a = alart.create();
                a.show();
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,ChangePassword.class);
                startActivity(intent);
            }
        });

    }

}