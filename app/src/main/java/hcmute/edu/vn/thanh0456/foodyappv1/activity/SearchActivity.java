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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
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

import java.util.ArrayList;
import java.util.Arrays;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.CuisineAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodResPageAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.SearchFoodAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.ProductDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.RestaurantDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class SearchActivity extends AppCompatActivity {
    EditText search_query;
    private SearchFoodAdaptor adapter;
    private RecyclerView recyclerViewFoodList;
    public ArrayList<SearchFoodDomain> menu;
    public ArrayList<CuisineDomain> cuisineDomains;
    ArrayList<String> session_listTypeSelected;
    ArrayList<String> session_checkBoxesSortSelected;
    String filter_query;
    ImageView filterBTN;
    ImageView backBTN;
    private RecyclerView recyclerViewCuisineList;
    private CuisineAdaptor cuisineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_search:
                    return true;
                case R.id.nav_favorite:
                    startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
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


        search_query = findViewById(R.id.search_query);
        Intent intent = getIntent();
        String s_query = intent.getStringExtra("s_query");
        session_listTypeSelected = intent.getStringArrayListExtra("type_selected");
        filter_query = intent.getStringExtra("filter_query");
        search_query.setText(s_query);
        if (s_query != null) {
                createExampleList(s_query, session_listTypeSelected, filter_query);
        }
        else {
            createExampleList("", session_listTypeSelected, filter_query);
        }
        recyclerViewFood();

        filterBTN = findViewById(R.id.filterbtn);
        search_query = findViewById(R.id.search_query);
        filterBTN.setOnClickListener(view -> {
            showDialog();
        });

        search_query.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        search_query.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String text = search_query.getText().toString();
                    createExampleList(text, null, null);
                    adapter.filterList(menu);
                    recyclerViewFood();
                }
                return false;
            }
        });
        backBTN = findViewById(R.id.backbtn);
        backBTN.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    private void filter(String text) {
        ArrayList<SearchFoodDomain> filtermenu = new ArrayList<>();
        for(SearchFoodDomain item : menu) {
            if (item.getProd_name().toLowerCase().contains(text.toLowerCase()) || item.getRes_name().toLowerCase().contains(text.toLowerCase())) {
                filtermenu.add(item);
            }
        }
        adapter.filterList(filtermenu);
    }
    private void createExampleList(String s_query, ArrayList<String> res_type, String filter_query) {
        ProductDAO productDAO = new ProductDAO(getApplicationContext());
        menu = productDAO.getAllProductOnSearch(s_query, res_type, filter_query);
    }
    private void recyclerViewFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewFoodList = findViewById(R.id.recyclerViewSearch);
        recyclerViewFoodList.setLayoutManager(linearLayoutManager);

        adapter = new SearchFoodAdaptor(this, menu);
        recyclerViewFoodList.setAdapter(adapter);
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

            String text = dialog_search_query.getText().toString();
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
                createExampleList(text, null, filter_query);
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
                text = dialog_search_query.getText().toString();
                createExampleList(text, listTypeSelected, filter_query);
            }
            adapter.filterList(menu);
            search_query.setText(text);
            dialog.dismiss();
        });
        recyclerViewCuisine(dialog);
        dialog.show();
    }
    private void createCuisineDomain() {
        RestaurantDAO restaurantDAO = new RestaurantDAO(this);
        cuisineDomains = restaurantDAO.getAllType();
    }
    private void recyclerViewCuisine(Dialog dialog) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerViewCuisineList = dialog.getWindow().findViewById(R.id.recyclerViewCuisine);
        recyclerViewCuisineList.setLayoutManager(linearLayoutManager);


        cuisineAdapter = new CuisineAdaptor(cuisineDomains, this);
        recyclerViewCuisineList.setAdapter(cuisineAdapter);
    }
}