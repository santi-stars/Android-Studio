package com.svalero.listacompra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.svalero.listacompra.database.AppDatabase;
import com.svalero.listacompra.domain.Product;
import com.svalero.listacompra.util.ImageUtils;

import java.io.ByteArrayOutputStream;

public class NewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        ImageView imageView = findViewById(R.id.product_image);
    }

    public void add(View view) {
        EditText etName = findViewById(R.id.product_name);
        EditText etCategory = findViewById(R.id.product_category);
        EditText etQuantity = findViewById(R.id.product_quantity);
        EditText etPrice = findViewById(R.id.product_price);
        CheckBox checkImportant = findViewById(R.id.important_product);
        ImageView productImageView = findViewById(R.id.product_image);

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
        byte[] productImage = ImageUtils.fromImageViewToByteArray(productImageView);
        // Llamamos a nuestro metodo en UTILS que convierte el imageview en un array de bytes

        Product product = new Product(name, category, Integer.parseInt(quantity), Float.parseFloat(price), important, productImage);
        // Crea un objeto con los edittext por parametro

        //Instancia de la base de datos
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "products").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        // Inserta el objeto product en la base de datos
        db.productDao().insert(product);

        Toast.makeText(this, "Producto " + name + " añadido", Toast.LENGTH_SHORT).show();

        etName.setText("");
        etCategory.setText("");
        etQuantity.setText("");
        etPrice.setText("");
        checkImportant.setChecked(false);
        etName.requestFocus();  //Pone el cursor en la casilla del nombre
    }
}