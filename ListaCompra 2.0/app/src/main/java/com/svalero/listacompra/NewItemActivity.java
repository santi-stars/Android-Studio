package com.svalero.listacompra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
    }

    public void add(View view) {
        EditText etName = findViewById(R.id.product_name);
        EditText etCategory = findViewById(R.id.product_category);
        EditText etQuantity = findViewById(R.id.product_quantity);
        EditText etPrice = findViewById(R.id.product_price);
        CheckBox checkImportant = findViewById(R.id.important_product);

        if (etName.getText().toString().equals("")) {
            Toast.makeText(this, "Debes escribir el nombre del producto", Toast.LENGTH_LONG).show();
            return;
        }

        String name = etName.getText().toString();
        String category = etCategory.getText().toString();
        String quantity = etQuantity.getText().toString();
        if (quantity.equals("")) {
            quantity = "1";
        }
        String price = etPrice.getText().toString();
        if (price.equals("")) {
            price = "0";
        }
        boolean important = checkImportant.isChecked();

        Product product = new Product(name, category, Integer.parseInt(quantity), Double.parseDouble(price), important);
        // Crea un objeto con los edittext por parametro

        MainActivity.products.add(product);
        // productsAdapter.notifyDataSetChanged(); // cada vez que haya cambios REFRESCA la lista
        Toast.makeText(this, "Producto " + name + " añadido", Toast.LENGTH_SHORT).show();

        etName.setText("");
        etCategory.setText("");
        etQuantity.setText("");
        etPrice.setText("");
        checkImportant.setChecked(false);
        etName.requestFocus();  //Pone el cursor en la casilla del nombre
    }
}