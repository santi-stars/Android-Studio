package com.svalero.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent(); // Creamos un objeto para recibir el intent de otra clase
        String name = intent.getStringExtra("name");
        String category = intent.getStringExtra("category");
        // Recibimos el intent al que hemos llamado name anteriormente
        TextView tvProductDetailName = findViewById(R.id.product_detail_name);
        tvProductDetailName.setText(name + " " + category); // Establecemos al TextView el nombre que recibimos por el intent
    }
}