package com.example.fadni.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    private EditText inputText;
    private TextView costText;
    private SeekBar percentBar;
    private TextView percentText;
    private TextView totalCut;
    private TextView totalCost;
    private NumberFormat percentageFormatter = NumberFormat.getPercentInstance();
    private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
    private double percentageValue;
    private double amountValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputText = (EditText) findViewById(R.id.inputText);
        costText = (TextView) findViewById(R.id.costText);
        percentBar = (SeekBar) findViewById(R.id.percentBar);
        percentText = (TextView) findViewById(R.id.percentText);
        totalCut = (TextView) findViewById(R.id.totalCut);
        totalCost = (TextView) findViewById(R.id.totalCost);

        percentBar.setOnSeekBarChangeListener(this);




        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    double value = Double.parseDouble(s.toString());
                    amountValue = value / 100;
                    costText.setText(currencyFormatter.format(amountValue));
                }
                else {
                    amountValue = 0;
                    costText.setText(currencyFormatter.format(amountValue));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                double value = Double.parseDouble(s.toString());
                double amountValue = value / 100;
                double tipValue = amountValue * percentageValue;
                double totalValue = tipValue + amountValue;
                totalCut.setText(currencyFormatter.format(tipValue));
                totalCost.setText(currencyFormatter.format(totalValue));
            }

        });


    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        percentageValue = progress / 100.0;
        percentText.setText(percentageFormatter.format(percentageValue));
        double tipValue = amountValue * percentageValue;
        double totalValue = tipValue + amountValue;
        totalCut.setText(currencyFormatter.format(tipValue));
        totalCost.setText(currencyFormatter.format(totalValue));


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
