package com.example.convrealdolar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat formatoReal = NumberFormat.getCurrencyInstance();
    private static final NumberFormat formatoDolar = NumberFormat.getCurrencyInstance(Locale.US);

    double montanteReal = 0.0;
    double cotacao = 3.93;


    private TextView textViewReal;
    private TextView textViewCotacao;
    private TextView textViewDolar;
    private SeekBar seekBarCotacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("ConvReal -> Dolar");


        textViewReal =  findViewById(R.id.textViewReal);
        textViewCotacao =  findViewById(R.id.textViewCotacao);
        textViewDolar = findViewById(R.id.textViewDolar);
        seekBarCotacao = findViewById(R.id.seekBarCotacao);
        textViewDolar.setText(formatoDolar.format(0));



        EditText editTextReal = findViewById(R.id.editTextReal);
        editTextReal.addTextChangedListener(editMontanteRealWatcher);

        SeekBar seekBarCotacao =findViewById(R.id.seekBarCotacao);
        seekBarCotacao.setMax(1000);
        seekBarCotacao.setOnSeekBarChangeListener(seekBarListener);
        seekBarCotacao.setProgress((int)(cotacao * 100.0));



    }
    private void calculaConversao()  {

        textViewCotacao.setText(formatoDolar.format(cotacao));

        double  montanteDolar = montanteReal / cotacao;
        textViewDolar.setText(formatoDolar.format(montanteDolar));
    }


    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar,int progress, boolean fromUser) {

            cotacao = (progress / 100.0);
             calculaConversao();
        }


        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher editMontanteRealWatcher = new TextWatcher() {


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {

                montanteReal = Double.parseDouble(s.toString())/100.0;
                textViewReal.setText(formatoReal.format(montanteReal));


            }catch (NumberFormatException e) {
                textViewReal.setText("");
                montanteReal = 0.0;


            }
            calculaConversao();
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    };


}
