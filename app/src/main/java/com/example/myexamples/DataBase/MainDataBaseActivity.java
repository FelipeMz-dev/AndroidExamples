package com.example.myexamples.DataBase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myexamples.R;

public class MainDataBaseActivity extends AppCompatActivity {

    EditText txtProductId, txtProductName, txtProductPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_data_base);

        txtProductId = findViewById(R.id.txtProductId);
        txtProductId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String text = txtProductId.getText().toString();
                if (text.length() == 1){
                    txtProductId.setText("00"+ text);
                }else if (text.length() == 2){
                    txtProductId.setText("0"+ text);
                }
            }
        });
        txtProductName = findViewById(R.id.txtProductName);
        txtProductPrice = findViewById(R.id.txtProductPrice);
    }

    public void dbCreate(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String id = txtProductId.getText().toString();
        String name = txtProductName.getText().toString();
        String price = txtProductPrice.getText().toString();

        if (id.isEmpty() || name.isEmpty() || price.isEmpty()){
            Toast.makeText(this, "Fill all the data's", Toast.LENGTH_SHORT).show();
        }else {
            ContentValues create = new ContentValues();

            create.put("id", id);
            create.put("name", name);
            create.put("price", price);

            long quantity = dataBase.insert("products", null, create);

            if (quantity > 0){
                Toast.makeText(this, "Product created", Toast.LENGTH_SHORT).show();
                txtProductId.setText("");
                txtProductName.setText("");
                txtProductPrice.setText("");
            }else {
                Toast.makeText(this, "Don't created", Toast.LENGTH_SHORT).show();
            }
        }
        dataBase.close();
    }

    public void dbRead(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String id = txtProductId.getText().toString();

        if (id.isEmpty()){
            Toast.makeText(this, "Fill the ID", Toast.LENGTH_SHORT).show();
        }else {
            Cursor row = dataBase.rawQuery("select name, price from products where id =" + id, null);

            if (row.moveToFirst()){
                txtProductName.setText(row.getString(0));
                txtProductPrice.setText(row.getString(1));
            }else {
                Toast.makeText(this, "Don't found", Toast.LENGTH_SHORT).show();
            }
        }
        dataBase.close();
    }

    public void dbUpdate(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String id = txtProductId.getText().toString();
        String name = txtProductName.getText().toString();
        String price = txtProductPrice.getText().toString();

        if (id.isEmpty() || name.isEmpty() || price.isEmpty()){
            Toast.makeText(this, "Fill all the data's", Toast.LENGTH_SHORT).show();
        }else {
            ContentValues update = new ContentValues();

            update.put("id", id);
            update.put("name", name);
            update.put("price", price);

            int quantity = dataBase.update("products", update, "id = " + id, null);

            if (quantity > 0){
                Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Don't found", Toast.LENGTH_SHORT).show();
            }

            txtProductId.setText("");
            txtProductName.setText("");
            txtProductPrice.setText("");
        }
        dataBase.close();
    }

    public void dbDelete(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase dataBase = admin.getWritableDatabase();

        String id = txtProductId.getText().toString();

        if (id.isEmpty()){
            Toast.makeText(this, "Fill the ID", Toast.LENGTH_SHORT).show();
        }else {
            int quantity = dataBase.delete("products", "id = " + id, null);

            txtProductId.setText("");
            txtProductName.setText("");
            txtProductPrice.setText("");
            
            if (quantity > 0){
                Toast.makeText(this, "Product deleted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Don't found", Toast.LENGTH_SHORT).show();
            }
        }
        dataBase.close();
    }
}