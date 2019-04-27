package com.example.administrator.a1606020053levanson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SanPhamItemActivity extends AppCompatActivity {
    EditText edt_ProductName,edt_price,edt_sdescription,edt_producer;
    String ProductName="", Price="", Description="", Producter="";
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham_item);
        onIt();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onValueday()){
                    ProductName = edt_ProductName.getText().toString();
                    Price = edt_price.getText().toString();
                    Description = edt_sdescription.getText().toString();
                    Producter = edt_producer.getText().toString();
                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("product_name",ProductName);
                    mMap.put("price",Price);
                    mMap.put("description", Description);
                    mMap.put("producer",Producter);
                    new LoginAsyncTask(SanPhamItemActivity.this, new ILoginView() {
                        @Override
                        public void onLoginSuccess(String m) {
                            Toast.makeText(SanPhamItemActivity.this,m,Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onLoginFail(String m) {
                            Toast.makeText(SanPhamItemActivity.this,m,Toast.LENGTH_SHORT).show();
                        }
                    },mMap).execute("http://www.vidophp.tk/api/account/dataaction?context=insert");

                }

            }
        });
    }

    private void onIt() {
        edt_ProductName = (EditText)findViewById(R.id.edt_new_ProductName);
        edt_price = (EditText)findViewById(R.id.edt_new_price);
        edt_sdescription = (EditText)findViewById(R.id.edt_new_sdescription);
        edt_producer = (EditText)findViewById(R.id.edt_new_producer);
        btnSave = (Button)findViewById(R.id.btn_add_new);
    }
    public boolean onValueday(){
        if(edt_ProductName.getText().toString().length()<1){
            edt_ProductName.setError("This is not Blank");
            return false;
        }
        if(edt_price.getText().toString().length()<1){
            edt_price.setError("This is not Blank");
            return false;
        }
        if(edt_sdescription.getText().toString().length()<1){
            edt_sdescription.setError("This is not Blank");
            return false;
        }
        if(edt_producer.getText().toString().length()<1){
            edt_producer.setError("This is not Blank");
            return false;
        }
        return true;
    }
}
