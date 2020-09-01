package com.packagename.shoppingapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.packagename.shoppingapp.R;
import com.packagename.shoppingapp.database.products.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {
    private ArrayList<Product> products;
    private RecyclerView recycler;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        products = Objects.requireNonNull(this.getIntent().getExtras()).getParcelableArrayList("cart");
        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        CartApdater adapter = new CartApdater(products);
        recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public class CartApdater extends RecyclerView.Adapter<CartApdater.CartApdaterHolder> {

        private List<Product> cartList;

        public CartApdater(List<Product> cartList) {
            this.cartList = cartList;
        }

        @NonNull
        @Override
        public CartApdater.CartApdaterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
            CartApdater.CartApdaterHolder productAdpaterHolder = new CartApdater.CartApdaterHolder(view);
            return productAdpaterHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull CartApdater.CartApdaterHolder holder, int position) {
//            final Product prd = cartList.get(position);

            holder.cart_title.setText(cartList.get(position).getProd_Name());
            holder.cart_price.setText(cartList.get(position).getProd_Price());

        }

        @Override
        public int getItemCount() {
            return cartList.size();
        }

        public class CartApdaterHolder extends RecyclerView.ViewHolder {

            public TextView cart_title;
            public TextView cart_price;

            public CartApdaterHolder(@NonNull View itemView) {
                super(itemView);
                cart_title = (TextView) itemView.findViewById(R.id.cart_title);
                cart_price = (TextView) itemView.findViewById(R.id.cart_price);
            }
        }
    }
}
