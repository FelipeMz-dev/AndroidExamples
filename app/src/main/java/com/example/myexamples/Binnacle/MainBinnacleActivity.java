package com.example.myexamples.Binnacle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamples.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainBinnacleActivity extends AppCompatActivity {

    private EditText txtBinnacle;
    SoundPool sp;
    int sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_binnacle);

        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);
        sound = sp.load(this, R.raw.power_up, 1);

        txtBinnacle = (EditText) findViewById(R.id.txtBinnacle);

        if (ExistFile("binnacle.txt")){
            try {
                InputStreamReader file = new InputStreamReader(openFileInput("binnacle.txt"));
                BufferedReader bufferedReader = new BufferedReader(file);
                String line = bufferedReader.readLine();
                String completeBinnacle = "";
                while (line != null){
                    completeBinnacle = completeBinnacle + line + "\n";
                    line = bufferedReader.readLine();
                }
                bufferedReader.close();
                file.close();
                txtBinnacle.setText(completeBinnacle);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    //return if exist the file
    private boolean ExistFile(String fileName){
        String files [] = fileList();
        for (String file:files) if (file.equals(fileName)) return true;
        return false;
    }
    //action with button save
    public void Save(View view){
        try {
            OutputStreamWriter file = new OutputStreamWriter(openFileOutput("binnacle.txt", Activity.MODE_PRIVATE));
            file.write(txtBinnacle.getText().toString());
            file.flush();
            file.close();
            Toast.makeText(this, "File Saved", Toast.LENGTH_SHORT).show();
            sp.play(sound,1, 1, 1, 0, 1);
            finish();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}