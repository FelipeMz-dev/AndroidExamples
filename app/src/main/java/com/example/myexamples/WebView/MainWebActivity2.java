package com.example.myexamples.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.myexamples.R;

public class MainWebActivity2 extends AppCompatActivity {

    WebView webView;
    EditText editTextURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web2);

        editTextURL = findViewById(R.id.editTextURL);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        editTextURL.setText(sharedPreferences.getString("url", ""));

        if (!editTextURL.getText().toString().isEmpty()){
            String url = editTextURL.getText().toString();
            webView.loadUrl(url);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("url", editTextURL.getText().toString());
        editor.apply();
    }

    public void SearchWeb(View view){
        String url = editTextURL.getText().toString();
        webView.loadUrl(url);
    }

}