package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodResPageAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.CategoryDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.ProductDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain2;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FoodResPageDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class RestaurantPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    ArrayList<CategoryDomain2> categories;
    int no_of_categories = -1;
    ImageView res_img;
    TextView name;
    TextView type;
    TextView time;
    TextView score;
    TextView rating;
    TextView address;
    DiscoverFoodPlaceDomain discoverFoodPlaceDomain;
    private FoodResPageAdaptor adapter;
    private  RecyclerView recyclerViewFoodList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_page);
        tabLayout = findViewById(R.id.tab_layout);
        res_img = findViewById(R.id.res_pic);
        name = findViewById(R.id.name);
        type = findViewById(R.id.typeRes);
        time = findViewById(R.id.serve_time);
        score = findViewById(R.id.score);
        rating = findViewById(R.id.rating);
        address = findViewById(R.id.address);

        loadRestaurantInfo();
        loadCategoryTabLayout();
        handleBottomNavigation();
        setupTabLayout();
        recyclerViewFood(0);

    }
    private void loadCategoryTabLayout() {
        CategoryDAO categoriesDAO = new CategoryDAO(this);
        categories = categoriesDAO.getAllCategoryByResId(discoverFoodPlaceDomain.getId());

        no_of_categories = categories.size();
        for(int i = 0; i < no_of_categories; i++) {
            String category_name = categories.get(i).getTitle();
            tabLayout.addTab(tabLayout.newTab().setText(category_name));
        }
    }
    private void loadRestaurantInfo() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        discoverFoodPlaceDomain = (DiscoverFoodPlaceDomain) bundle.get("obj_restaurant");
        int resId = getApplicationContext().getResources()
                .getIdentifier(discoverFoodPlaceDomain.getImg(), "drawable", getApplicationContext().getPackageName());
        res_img.setImageResource(resId);
        name.setText(discoverFoodPlaceDomain.getName());
        type.setText(discoverFoodPlaceDomain.getType());
        time.setText(MessageFormat.format("{0} mins", discoverFoodPlaceDomain.getServe_time()));
        score.setText(String.valueOf(discoverFoodPlaceDomain.getScore()));
        rating.setText(MessageFormat.format("({0})", discoverFoodPlaceDomain.getRating()));
        address.setText(String.format("%s,...", discoverFoodPlaceDomain.getAddress()));
    }
    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                onTabTapped(tab.getPosition());
                recyclerViewFood(tab.getPosition());
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
                Toast.makeText(this, categories.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
    private void recyclerViewFood(int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerViewFoodResPage);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        ProductDAO productDAO = new ProductDAO(getApplicationContext());
        ArrayList<ProductDomain> menu = productDAO.getAllProductOfResByCategory(discoverFoodPlaceDomain.getId(), categories.get(position).getCategory_id());

        adapter = new FoodResPageAdaptor(this, menu);
        recyclerViewFoodList.setAdapter(adapter);
    }
    private void handleBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.getMenu().setGroupCheckable(0, true, false);
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if(menu.getItem(i).isChecked()) {
                menu.getItem(i).setChecked(false);
            }
        }
        bottomNavigationView.getMenu().setGroupCheckable(0, true, true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_favorite:
                    startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_order:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(adapter != null) {
            adapter.release();
        }
    }
}
