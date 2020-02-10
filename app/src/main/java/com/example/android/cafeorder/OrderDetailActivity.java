package com.example.android.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {

    private String textOrderDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        textOrderDetail = intent.getStringExtra("fullOrderStr");

        getTextOrderDetail();

    }

   private void getTextOrderDetail(){
       TextView textView = (TextView) findViewById(R.id.textViewOrderDetail);
       textView.setText(textOrderDetail);
    }
}
