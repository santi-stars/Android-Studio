package com.svalero.listacompra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.svalero.listacompra.database.AppDatabase;
import com.svalero.listacompra.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public List<Product> products;
    private ArrayAdapter<Product> productsAdapter;   //ArrayList de ANDROID que enlaza ArrayList
    // con el ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>(); // Inicia el List que guardara los productos
        ListView lvProducts = findViewById(R.id.products_list); // Inicia el ListView
        productsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        //ArrayAdapter lo construimos(contexto(activity donde estoy), layaout elemento simple de lista,
        // products (ArrayList))
        lvProducts.setAdapter(productsAdapter); //lvProducts haz lo que diga el productAdapter

        lvProducts.setOnItemClickListener(this); // para que esté atento a los click en los items

    }

    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
        makeSummary();
    }

    public void refreshList() {
        loadProducts();
        productsAdapter.notifyDataSetChanged();
    }

    private void loadProducts() {
        products.clear(); // Limpia el objeto products // NUNCA hacer un NEW
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "products").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        products.addAll(db.productDao().getAll()); // Vuelve a cargar todos los productos
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.new_item) {
            Intent intent = new Intent(this, NewItemActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = products.get(position);

        Intent intent = new Intent(this, ProductDetail.class);
        intent.putExtra("name", product.getName()); // "name" es el nombre con el que queremos mandar el dato
        // en este mandamos en el intent el nombre del producto, podemos pasar otros tipos de datos u objetos
        intent.putExtra("category", product.getCategory()); // mandamos en el intent el nombre del producto

        // PRUEBA MANDAR IMAGEN A PRODUCT DETAIL
        // intent.putExtra("image", product.getImage());
        startActivity(intent);
    }
}