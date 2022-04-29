package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodCartAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FoodCartDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCartList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);

        recyclerViewCategory();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCartList = findViewById(R.id.recyclerViewCart);
        recyclerViewCartList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodCartDomain> category = new ArrayList<>();
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));
        category.add(new FoodCartDomain("kisspng_hamburg_", "Burger Beef", "M", 12.99, 2));


        adapter = new FoodCartAdaptor(category);
        recyclerViewCartList.setAdapter(adapter);
    }
}
