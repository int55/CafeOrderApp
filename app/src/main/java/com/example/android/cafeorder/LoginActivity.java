package com.example.android.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        //скрываем строку состояния в самом верху
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editTextName = (EditText) findViewById(R.id.editNameXML);
        editTextPass = (EditText) findViewById(R.id.editPassXML);
    }

    public void onClickCreateOrder(View view) {
        String name = editTextName.getText().toString().trim(); // .trim() метод убирает лишние пробелы
        String password = editTextPass.getText().toString().trim();
            if (!name.isEmpty() && !password.isEmpty()) {
                Intent intent = new Intent(this, CreateOrderActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("password", password);
                startActivity(intent);
            }else {
                Toast.makeText(this, R.string.warning_toast, Toast.LENGTH_SHORT).show();
            }
    }
}
