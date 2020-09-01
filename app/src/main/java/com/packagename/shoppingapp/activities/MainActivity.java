package com.packagename.shoppingapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.packagename.shoppingapp.R;
import com.packagename.shoppingapp.database.products.Product;
import com.packagename.shoppingapp.database.products.ProductDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductDatabase productDatabase;
    private List<Product> productList = new ArrayList<>();
    public static List<Product> cartProduct = new ArrayList<>();
    private RecyclerView recycler_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productDatabase = Room.databaseBuilder(getApplicationContext(), ProductDatabase.class, "sample").build();

        recycler_product = findViewById(R.id.recycler_product);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (!sharedPreferences.getBoolean("FIRSTTIME", false)) {
            new ProductAsync().execute();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("FIRSTTIME", true);
            editor.apply();
        }

        new ProductDB().execute();
    }

    private class ProductAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            Product product = new Product();
            product.setProd_Name("Iphone");
            product.setProd_Price("Rs.70,000");
            productDatabase.getProductDAO().insert(product);

            Product product1 = new Product();
            product1.setProd_Name("Oppo");
            product1.setProd_Price("Rs.39,000");
            productDatabase.getProductDAO().insert(product1);

            Product product2 = new Product();
            product2.setProd_Name("Nokia");
            product2.setProd_Price("Rs.20,000");
            productDatabase.getProductDAO().insert(product2);

            Product product3 = new Product();
            product3.setProd_Name("Moto");
            product3.setProd_Price("Rs.50,000");
            productDatabase.getProductDAO().insert(product3);

            Product product4 = new Product();
            product4.setProd_Name("Realme");
            product4.setProd_Price("Rs.10,000");
            productDatabase.getProductDAO().insert(product4);

            Product product5 = new Product();
            product5.setProd_Name("Samsung");
            product5.setProd_Price("Rs.60,000");
            productDatabase.getProductDAO().insert(product5);

            Product product6 = new Product();
            product6.setProd_Name("Mi");
            product6.setProd_Price("Rs.7,000");
            productDatabase.getProductDAO().insert(product6);

            Product product7 = new Product();
            product7.setProd_Name("Vivo");
            product7.setProd_Price("Rs.9,000");
            productDatabase.getProductDAO().insert(product7);

            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class ProductDB extends AsyncTask<Void, Void, Void> {
        private List<Product> newProductList = new ArrayList<>();

        @Override
        protected Void doInBackground(Void... voids) {
            newProductList = productDatabase.getProductDAO().getProductList();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            productList = newProductList;

            ProductApdater adapter = new ProductApdater(productList);
            recycler_product.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            recycler_product.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    public static Product productPos;

    public class ProductApdater extends RecyclerView.Adapter<ProductApdater.ProductAdpaterHolder> {

        private List<Product> productList;

        public ProductApdater(List<Product> productLists) {
            productList = new ArrayList<>();
            cartProduct = new ArrayList<>();
            this.productList = productLists;
        }

        @NonNull
        @Override
        public ProductApdater.ProductAdpaterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
            ProductApdater.ProductAdpaterHolder productAdpaterHolder = new ProductApdater.ProductAdpaterHolder(view);
            return productAdpaterHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ProductApdater.ProductAdpaterHolder holder, final int position) {
            final Product prd = productList.get(position);
            holder.product_name.setText(productList.get(position).getProd_Name());
            holder.product_price.setText(productList.get(position).getProd_Price());

            holder.con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productPos = productList.get(position);
                    ArrayList<Product> sendProd = new ArrayList<Product>();
                    Intent productIntent = new Intent(MainActivity.this, ProductDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    Product prd = new Product();
                    prd.setProd_Name(productList.get(position).getProd_Name());
                    prd.setProd_Price(productList.get(position).getProd_Price());
                    sendProd.add(prd);
                    bundle.putParcelableArrayList("cart", sendProd);
                    productIntent.putExtras(bundle);
                    startActivity(productIntent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        public class ProductAdpaterHolder extends RecyclerView.ViewHolder {

            public RelativeLayout con;
            public TextView product_name;
            public TextView product_price;

            public ProductAdpaterHolder(@NonNull View itemView) {
                super(itemView);
                con = (RelativeLayout) itemView.findViewById(R.id.con);
                product_name = (TextView) itemView.findViewById(R.id.product_name);
                product_price = (TextView) itemView.findViewById(R.id.product_price);
            }
        }
    }
}
