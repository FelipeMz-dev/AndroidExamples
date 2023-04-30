package com.example.myexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myexamples.Binnacle.MainBinnacleActivity;
import com.example.myexamples.DataBase.MainDataBaseActivity;
import com.example.myexamples.WebView.MainWebActivity2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int idItem = item.getItemId();
        Intent intent = new Intent(this, MainActivity.class);
        switch (idItem){
            case R.id.item_binnacle:
                intent = new Intent(this, MainBinnacleActivity.class);
                break;
            case R.id.item_web_view:
                intent = new Intent(this, MainWebActivity2.class);
                break;
            case R.id.item_data_base:
                intent = new Intent(this, MainDataBaseActivity.class);
                break;
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
    public void goBinnacle(View view){
        Intent intent = new Intent(this, MainBinnacleActivity.class);
        startActivity(intent);
    }
    public void goWebView(View view){
        Intent intent = new Intent(this, MainWebActivity2.class);
        startActivity(intent);
    }
    public void goDataBase(View view){
        Intent intent = new Intent(this, MainDataBaseActivity.class);
        startActivity(intent);
    }
    public void goPhotographer(View view){
        Intent intent = new Intent(this, MainPhotographerActivity.class);
        startActivity(intent);
    }
}