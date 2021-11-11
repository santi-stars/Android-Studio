package com.svalero.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> products;
    private ArrayAdapter<Product> productsAdapter;   //ArrayList de ANDROID que enlaza ArrayList
    // con el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();

        ListView lvProducts = findViewById(R.id.products_list);
        productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        //ArrayAdapter lo construimos(contexto(activity donde estoy), layaout elemento simple de lista,
        // products (ArrayList))
        lvProducts.setAdapter(productsAdapter); //lvProducts haz lo que diga el productAdapter
    }

    public void add(View view) {
        EditText etName = findViewById(R.id.product_name);
        EditText etQuantity = findViewById(R.id.product_quantity);
        EditText etPrice = findViewById(R.id.product_price);

        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, "Debes escribir el nombre del producto", Toast.LENGTH_LONG).show();
            return;
        }

        String name = etName.getText().toString();
        String quantity = etQuantity.getText().toString();
        if (quantity.equals("")) {
            quantity = "1";
        }
        String price = etPrice.getText().toString();
        if (price.equals("")) {
            price = "0";
        }

        Product product = new Product(name, Integer.parseInt(quantity), Double.parseDouble(price));
        // Crea un objeto con los edittext por parametro

        products.add(product);
        productsAdapter.notifyDataSetChanged(); // cada vez que haya cambios REFRESCA la lista
        makeSummary();

        etName.setText("");
        etQuantity.setText("");
        etPrice.setText("");

        etName.requestFocus();  //Pone el cursor en la casilla del nombre
    }

    private void makeSummary() {
        int productCount = products.size();
        double totalPrice = products.stream()   // Coje la lista de prodcuctos, lo mete en un stream
                .map(Product::getPrice) //Pasa a tener una lista solo con los precios
                .mapToDouble(price -> price).sum(); //Suma todos esos precios
        // Se supone que hace la lista de todos los precios de los objetos
        TextView tvSummary = findViewById(R.id.summary);
        tvSummary.setText("Lista de la compra: " + productCount + " productos = " + totalPrice + "€");
    }
}