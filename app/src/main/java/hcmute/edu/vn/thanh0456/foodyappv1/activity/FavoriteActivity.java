package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FavoriteFoodAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.SearchFoodAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.ProductDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class FavoriteActivity extends AppCompatActivity {
    private FavoriteFoodAdaptor favoriteFoodAdaptor;
    private RecyclerView recyclerViewFoodList;
    ArrayList<SearchFoodDomain> favoriteFoodList;
    ConstraintLayout fav_empty_layout;
    ImageView backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        fav_empty_layout = findViewById(R.id.fav_empty_layout);
        backbtn = findViewById(R.id.backbtn);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_favorite);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_favorite:
                    return true;
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_order:
                    startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                    overridePendingTransition(0, 0);
                    return true;

                case R.id.nav_profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        });
        backbtn.setOnClickListener(view -> {
            onBackPressed();
        });
        createExampleList();
        recyclerViewFood();
        updateLayoutFavorite();
    }
    public void updateLayoutFavorite() {
        if(favoriteFoodList.size() != 0) {
            fav_empty_layout.setVisibility(View.INVISIBLE);
        }
        else {
            fav_empty_layout.setVisibility(View.VISIBLE);
        }
    }
    private void createExampleList() {
        ProductDAO productDAO = new ProductDAO(getApplicationContext());
        favoriteFoodList = productDAO.getAllProductFavorite();
    }
    private void recyclerViewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerViewFavorite);

        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        favoriteFoodAdaptor = new FavoriteFoodAdaptor(this, favoriteFoodList);
        recyclerViewFoodList.setAdapter(favoriteFoodAdaptor);
    }
}