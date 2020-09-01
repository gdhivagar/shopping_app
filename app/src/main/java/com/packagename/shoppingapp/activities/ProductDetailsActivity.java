package com.packagename.shoppingapp.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.packagename.shoppingapp.R;
import com.packagename.shoppingapp.database.carts.Cart;
import com.packagename.shoppingapp.database.products.Product;

import java.util.ArrayList;
import java.util.Objects;

public class ProductDetailsActivity extends AppCompatActivity {

    private TextView text_title;
    private TextView text_price;
    private Button btn_cart, btn_buyNow;
    private ArrayList<Product> cartProduct;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        text_title = (TextView) findViewById(R.id.text_title);
        text_price = (TextView) findViewById(R.id.text_price);
        btn_cart = (Button) findViewById(R.id.btn_cart);
        btn_buyNow = (Button) findViewById(R.id.btn_buyNow);

        cartProduct = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("cart");

        for (Product prd : cartProduct) {
            text_title.setText(prd.getProd_Name());
            text_price.setText(prd.getProd_Price());
        }

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!MainActivity.cartProduct.contains(MainActivity.productPos))
                    MainActivity.cartProduct.add(MainActivity.productPos);
                Toast.makeText(ProductDetailsActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
                ArrayList<Product> sendProd = (ArrayList<Product>) MainActivity.cartProduct;
                Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
                Bundle b = new Bundle();
                b.putParcelableArrayList("cart", sendProd);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }
}
