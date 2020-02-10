package com.example.android.cafeorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateOrderActivity extends AppCompatActivity {

    private TextView textViewHello;
    private TextView textViewAdditions;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxLemon;
    private Spinner spinnerTea;
    private Spinner spinnerCoffee;

    private String drink;
    private String name;
    private String password;

    private StringBuilder builderAdditions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        textViewHello = (TextView) findViewById(R.id.textViewHello);
        textViewAdditions = (TextView) findViewById(R.id.textViewAdditions);
        checkBoxMilk = (CheckBox) findViewById(R.id.checkboxMilk);
        checkBoxSugar = (CheckBox) findViewById(R.id.checkboxSugar);
        checkBoxLemon = (CheckBox) findViewById(R.id.checkboxLemon);
        spinnerTea = (Spinner) findViewById(R.id.spinnerTea);
        spinnerCoffee = (Spinner) findViewById(R.id.spinnerCoffee);


        drink = getString(R.string.radio_button_Tea);

        Intent intent = getIntent();
        if(intent.hasExtra("name") && intent.hasExtra("password")) {
            name = intent.getStringExtra("name");
            password = intent.getStringExtra("password");
        }
        else {
            name = getString(R.string.default_name);
            password = getString(R.string.default_password);
        }

        // 1 - строка приветствия
        String hello = String.format(getString(R.string.hello_userXML), name);
        textViewHello.setText(hello);

        builderAdditions = new StringBuilder();
    }

    public void onClickChangeDrink(View view) {
        RadioButton radioButton = (RadioButton) view;
        int id = radioButton.getId();
        if(id == R.id.radioButtonTea) {
            drink = getString(R.string.radio_button_Tea);
            spinnerTea.setVisibility(View.VISIBLE);
            spinnerCoffee.setVisibility(View.INVISIBLE);
            checkBoxLemon.setVisibility(View.VISIBLE);
        } else
             if(id == R.id.radioButtonCoffee) {
                 drink = getString(R.string.radio_button_Coffee);
                 spinnerCoffee.setVisibility(View.VISIBLE);
                 spinnerTea.setVisibility(View.INVISIBLE);
                 checkBoxLemon.setVisibility(View.INVISIBLE);
             }

        // 2 - строка выбора добавок
        String additions = getString(R.string.additionsXML);
        textViewAdditions.setText(additions);
    }

    public void onClickSendOrder(View view) {
        builderAdditions.setLength(0); // очистить значения стринг билдер
        if(checkBoxMilk.isChecked()){ // отмечен пункт или нет
            builderAdditions.append(getString(R.string.milk_check_box)).append(" ");
        }
        if(checkBoxSugar.isChecked()){
            builderAdditions.append(getString(R.string.sugar_check_box)).append(" ");
        }
        if(checkBoxLemon.isChecked() && drink.equals(getString(R.string.radio_button_Tea))){  // если это чай и выбран лимон
            builderAdditions.append(getString(R.string.lemon_check_box)).append(" ");
        }

        String spinnerDrinkSelect = ""; //получение значения из спиннера
        if(drink.equals(getString(R.string.radio_button_Tea))){
            spinnerDrinkSelect = spinnerTea.getSelectedItem().toString();
        }else if (drink.equals(getString(R.string.radio_button_Coffee))){
            spinnerDrinkSelect = spinnerCoffee.getSelectedItem().toString();
        }

        //Формируем строку заказа
        String orderStr =  String.format(getString(R.string.order_str), name, password, drink, spinnerDrinkSelect);

        //формируем строку с добавками
        String additionsStr;
        if(builderAdditions.length() > 0){ // если есть значения
            additionsStr = getString(R.string.need_additions) + " " + builderAdditions.toString();
        } else {
            additionsStr = getString(R.string.need_additions) + " " + getString(R.string.additions_str_not);
        }

        // полная строка заказа
        String fullOrderStr = orderStr + additionsStr;


        Intent intent = new Intent(this, OrderDetailActivity.class);
        intent.putExtra("fullOrderStr", fullOrderStr);
        startActivity(intent);

    }
}
