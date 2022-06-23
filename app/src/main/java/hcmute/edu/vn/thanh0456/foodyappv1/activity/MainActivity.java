package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.CategoryAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.CuisineAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.DiscoverFoodPlaceAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.CustomerDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.DiscoverFoodPlaceDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.RestaurantDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain;

public class MainActivity extends AppCompatActivity {
    private DiscoverFoodPlaceAdaptor discoverAdapter;
    private CategoryAdaptor categoryAdapter;
    private CuisineAdaptor cuisineAdapter;
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView recyclerViewDiscoverFoodList;
    private RecyclerView recyclerViewCuisineList;
    public ArrayList<CuisineDomain> cuisineDomains;
    ArrayList<String> session_listTypeSelected;
    ArrayList<String> session_checkBoxesSortSelected;
    TextView name;
    ShapeableImageView pro_pic;
    ImageView filterBTN;
    EditText search_query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleBottomNavigation();

        recyclerViewCategory();
        recyclerViewDiscoverFood();
        name = findViewById(R.id.name);
        pro_pic = findViewById(R.id.pic);
        filterBTN = findViewById(R.id.filterbtn);
        search_query = findViewById(R.id.search_query);
        filterBTN.setOnClickListener(view -> {
            showDialog();
        });

        Session session = new Session(getApplicationContext());
        CustomerDomain customerDomain = session.getSession("obj_customer");
        if(customerDomain.getFname() == null || customerDomain.getLname() == null || customerDomain.getPro_pic() == null) {
            name.setText("New User");
            customerDomain.setPro_pic("profile_pic");
        }
        else {
            name.setText(MessageFormat.format("{0} {1}", customerDomain.getFname(), customerDomain.getLname()));
        }
        int resId = getApplicationContext().getResources()
                .getIdentifier(customerDomain.getPro_pic(), "drawable", getApplicationContext().getPackageName());
        pro_pic.setImageResource(resId);

        search_query.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {

                    String text = search_query.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    intent.putExtra("s_query", text);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView2);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category = new ArrayList<>();
        category.add(new CategoryDomain(0,"Burger", "hamburger"));
        category.add(new CategoryDomain(1,"Gà", "pizza"));
        category.add(new CategoryDomain(2,"Trà sữa", "hot_dog"));
        category.add(new CategoryDomain(3,"Phở", "noodles"));
        category.add(new CategoryDomain(4,"Bún", "rice"));

        categoryAdapter = new CategoryAdaptor(this, category);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
    }
    private void recyclerViewDiscoverFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDiscoverFoodList = findViewById(R.id.recyclerView);
        recyclerViewDiscoverFoodList.setLayoutManager(linearLayoutManager);

        DiscoverFoodPlaceDAO discoverFoodPlaceDAO = new DiscoverFoodPlaceDAO(this);
        ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains = discoverFoodPlaceDAO.getAll();
//        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_1", "Andy @ Cindy's Diner", "87 Botsiord Circle Apt", 4.8, 233, "Free Delivery"));
//        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_2", "The Lady & Sons ", "102 West Congress Street", 4.9, 198, "Free Delivery"));
//        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_3", "The Saffron Boutique", "Sector 104, Noida", 4.7, 156, "30% OFF"));
//        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_4", "The Great Impasta", "42 Maine St. Brunswick", 4.9, 599, "10% OFF"));
//        discoverFoodPlaceDomains.add(new DiscoverFoodPlaceDomain("dis_5", "Pho Shizzle", "1314 Union Ave NE Ste 8, Renton", 4.5, 67, "Free Delivery"));

        discoverAdapter = new DiscoverFoodPlaceAdaptor(this, discoverFoodPlaceDomains);
        recyclerViewDiscoverFoodList.setAdapter(discoverAdapter);
    }
    private void recyclerViewCuisine(Dialog dialog) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCuisineList = dialog.getWindow().findViewById(R.id.recyclerViewCuisine);
        recyclerViewCuisineList.setLayoutManager(linearLayoutManager);


        cuisineAdapter = new CuisineAdaptor(cuisineDomains, this);
        recyclerViewCuisineList.setAdapter(cuisineAdapter);
    }
    private void showDialog() {
        createCuisineDomain();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.filter_bottomsheet_layout);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFilterAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        TextView dialog_search_query = dialog.getWindow().findViewById(R.id.search_query);
        TextView applyfilter = dialog.getWindow().findViewById(R.id.applyfilter);
        CheckBox topscored = dialog.getWindow().findViewById(R.id.topscored);
        CheckBox toprated = dialog.getWindow().findViewById(R.id.toprated);
        CheckBox highlow = dialog.getWindow().findViewById(R.id.highlow);
        CheckBox lowhigh = dialog.getWindow().findViewById(R.id.lowhigh);
        ArrayList<CheckBox> checkBoxesSort = new ArrayList<>(Arrays.asList(topscored, toprated, highlow, lowhigh));


        Session session = new Session(getApplicationContext());
        session_listTypeSelected = session.getSessionFilter("listTypeSelected");
        session_checkBoxesSortSelected = session.getSessionFilter("checkBoxesSortSelected");
        if(session_listTypeSelected != null) {
            for (String item : session_listTypeSelected) {
                for (CuisineDomain cuisineDomainItem : cuisineDomains) {
                    if (cuisineDomainItem.getName().equals(item)) {
                        cuisineDomainItem.setSelected(!cuisineDomainItem.isSelected());
                        break;
                    }
                }
            }
        }

        if(session_checkBoxesSortSelected != null) {
            for (String item : session_checkBoxesSortSelected) {
                if (topscored.getText().toString().equals(item)) {
                    topscored.setChecked(true);
                }
                else if (toprated.getText().toString().equals(item)) {
                    toprated.setChecked(true);
                }
                else if (highlow.getText().toString().equals(item)) {
                    highlow.setChecked(true);
                }
                else if (lowhigh.getText().toString().equals(item)) {
                    lowhigh.setChecked(true);
                }
            }
        }

        applyfilter.setOnClickListener(view -> {
            ArrayList<String> listTypeSelected = new ArrayList<>();
            for(CuisineDomain item : cuisineDomains) {
                if(item.isSelected()) {
                    listTypeSelected.add(item.getName());
                }
            }
            ArrayList<String> checkBoxesSortSelected = new ArrayList<>();
            for (CheckBox item : checkBoxesSort) {
                if (item.isChecked()) {
                    checkBoxesSortSelected.add(item.getText().toString());
                }
            }
//            String listString = TextUtils.join(", ", listTypeSelected);
            session.storeSessionFilter("listTypeSelected", listTypeSelected);
            session.storeSessionFilter("checkBoxesSortSelected", checkBoxesSortSelected);


            String filter_query = null;

            ////////
            if (listTypeSelected.size() == 0) {
                if (checkBoxesSortSelected != null) {
                    for (String item : checkBoxesSortSelected) {
                        if (item.equals("Top Scored")) {
                            filter_query = "restaurant.score DESC";
                        }
                        else if (item.equals("Top Rated")) {
                            filter_query = "restaurant.rating DESC";
                        }
                        else if (item.equals("High - Low")) {
                            filter_query = "product.price DESC";
                        }
                        else if (item.equals("Low - High")) {
                            filter_query = "product.price ASC";
                        }
                    }
                }
            }
            else {
                while(listTypeSelected.size() < 4) {
                    listTypeSelected.add("");
                }
                if (checkBoxesSortSelected != null) {
                    for (String item : checkBoxesSortSelected) {
                        if (item.equals("Top Scored")) {
                            filter_query = "restaurant.score DESC";
                        }
                        else if (item.equals("Top Rated")) {
                            filter_query = "restaurant.rating DESC";
                        }
                        else if (item.equals("High - Low")) {
                            filter_query = "product.price DESC";
                        }
                        else if (item.equals("Low - High")) {
                            filter_query = "product.price ASC";
                        }
                    }
                }
            }
            String text = dialog_search_query.getText().toString();
            Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
            intent.putExtra("s_query", text);
            intent.putExtra("type_selected", listTypeSelected);
            intent.putExtra("filter_query", filter_query);
            startActivity(intent);
            dialog.dismiss();
        });
        recyclerViewCuisine(dialog);
        dialog.show();
    }
    private void createCuisineDomain() {
        RestaurantDAO restaurantDAO = new RestaurantDAO(this);
        cuisineDomains = restaurantDAO.getAllType();
    }
    private void handleBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_explore);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_explore:
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(discoverAdapter != null) {
            discoverAdapter.release();
        }
        if(categoryAdapter != null) {
            categoryAdapter.release();
        }
    }
}