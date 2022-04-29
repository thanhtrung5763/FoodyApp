package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodResPageAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FoodResPageDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class RestaurantPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    ArrayList<String> category;
    int no_of_categories = -1;

    private RecyclerView.Adapter adapter;
    private  RecyclerView recyclerViewFoodList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_page);
        tabLayout = findViewById(R.id.tab_layout);

        category = new ArrayList<String>(Arrays.asList("Burger", "Pizza", "Taco", "Hotdog", "Soup", "Drink"));
        no_of_categories = category.size();
        for(int i = 0; i < no_of_categories; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(category.get(i)));
        }
        setupTabLayout();
        recyclerViewFood();
    }
    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabTapped(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabTapped(tab.getPosition());
            }
        });
    }
    private void onTabTapped(int position) {
        switch (position) {
            case 0:
                break;
            default:
                Toast.makeText(this, category.get(position), Toast.LENGTH_SHORT).show();
        }
    }
    private void recyclerViewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodResPage);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodResPageDomain> menu = new ArrayList<>();
        menu.add(new FoodResPageDomain("burger_1", "Burger Beef", 12.99));
        menu.add(new FoodResPageDomain("burger_1", "Burger Shrimp", 12.00));
        menu.add(new FoodResPageDomain("burger_1", "Burger Fish", 13.99));
        menu.add(new FoodResPageDomain("burger_1", "Burger Chicken",11.49));
        menu.add(new FoodResPageDomain("burger_1", "Burger Deep",9.69));
        menu.add(new FoodResPageDomain("burger_1", "Burger Double Beef", 15.99));


        adapter = new FoodResPageAdaptor(menu);
        recyclerViewFoodList.setAdapter(adapter);
    }
}
