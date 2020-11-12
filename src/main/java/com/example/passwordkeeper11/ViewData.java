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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {

    ListView listView;
    DBhelper mydb;
    ArrayList<String> arrayemail, arraypass;
    CustomAdapter adapter;

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    AlertDialog.Builder alart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        listView=findViewById(R.id.list_item);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawerly);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.m_home) {
                    Intent intent = new Intent(ViewData.this,Home.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_about) {
                    Intent intent = new Intent(ViewData.this, AboutMe.class);
                    startActivity(intent);
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (menuItem.getItemId() == R.id.m_logout) {
                    Intent intent = new Intent(ViewData.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        mydb = new DBhelper(this);

        arrayemail = new ArrayList<>();
        arraypass = new ArrayList<>();

        viewData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, final long id) {
                alart=new AlertDialog.Builder(ViewData.this);
                alart.setTitle("Are you sure?");
                alart.setMessage("Are you sure want to clear  this data?");
                alart.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        TextView tmp=view.findViewById(R.id.listemail);
                        String mail=tmp.getText().toString();

                        String ID=getid(mail);

                        boolean ans=mydb.deleteTitle(ID);
                        if(ans==true)
                            Toast.makeText(getApplicationContext(),"Deleted "+mail,Toast.LENGTH_SHORT).show();
                        else Toast.makeText(getApplicationContext(),"Failed to delete",Toast.LENGTH_SHORT).show();
                        refresh();

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

    }

    private String getid(String mail) {
        Cursor res = mydb.getData();
        String id,email;
            while (res.moveToNext()) {
                email=res.getString(1)+"\n";
                id=res.getString(0)+"\n";
                if(email.equals(mail)){
                    return id;
                }
            }
        return "";

    }

    public void viewData() {
        Cursor res = mydb.getData();
        if (res.getCount() == 0) {
            Toast.makeText(ViewData.this, "No Data Found", Toast.LENGTH_SHORT);
        } else {
            while (res.moveToNext()) {
                arrayemail.add(res.getString(1) + "\n");
                arraypass.add(res.getString(2) + "\n");
            }
            adapter = new CustomAdapter(this, arrayemail, arraypass);
            listView.setAdapter(adapter);
        }

    }
    public void refresh(){
        arraypass.clear();
        arrayemail.clear();
        viewData();
        adapter.notifyDataSetChanged();
    }

}