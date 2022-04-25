package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.CategoryAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.DiscoverFoodPlaceAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewDiscoverFoodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewDiscoverFood();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView2);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain("Burger", "cat_1"));
        category.add(new CategoryDomain("Pizza", "cat_2"));
        category.add(new CategoryDomain("Taco", "cat_3"));
        category.add(new CategoryDomain("Soup", "cat_4"));
        category.add(new CategoryDomain("Drink", "cat_5"));

        adapter = new CategoryAdaptor(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewDiscoverFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDiscoverFoodList = findViewById(R.id.recyclerView);
        recyclerViewDiscoverFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains = new ArrayList<>();
        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_1", "Andy @ Cindy's Diner", "87 Botsiord Circle Apt", 4.8, 233, "Free Delivery"));
        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_2", "The Lady & Sons ", "102 WEST CONGRESS STREET", 4.9, 198, "Free Delivery"));
        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_3", "The Saffron Boutique", "Sector 104, Noida", 4.7, 156, "30% OFF"));
        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_4", "The Great Impasta", "42 Maine St. Brunswick", 4.9, 599, "10% OFF"));
        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_5", "Pho Shizzle", "1314 Union Ave NE Ste 8, Renton", 4.5, 67, "Free Delivery"));

        adapter = new DiscoverFoodPlaceAdaptor(discoverFoodPlaceDomains);
        recyclerViewDiscoverFoodList.setAdapter(adapter);
    }
}