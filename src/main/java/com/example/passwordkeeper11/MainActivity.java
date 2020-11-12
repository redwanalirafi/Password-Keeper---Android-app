package com.example.passwordkeeper11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tittle;
    EditText etpassword;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        

        tittle=findViewById(R.id.tittleid);
        etpassword=findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/font.otf");
        tittle.setTypeface(typeface);


        defaultpass();
        //Writing Data
        /*SharedPreferences sharedPreferences=getSharedPreferences("logindata", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Passwordkey","");
        editor.commit();*/


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputpass=etpassword.getText().toString();
                etpassword.setText("");
                SharedPreferences sh = getSharedPreferences("logindata", Context.MODE_PRIVATE);
                String password=sh.getString("Passwordkey","12345");

                /*//dev mode srt
                Intent intent=new Intent(MainActivity.this,Home.class);
                startActivity(intent);
                //end*/

                if(password.equals(inputpass)){
                    Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void defaultpass() {
        SharedPreferences de = getSharedPreferences("defaultpass", Context.MODE_PRIVATE);
        String tmp=de.getString("defaultpass","false");
        if(tmp.equals("false")){
            Toast.makeText(getApplicationContext(), "Default Password : 12345", Toast.LENGTH_LONG).show();

            SharedPreferences sharedPreferences = getSharedPreferences("defaultpass", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("defaultpass","true");
            editor.commit();
        }

    }

}