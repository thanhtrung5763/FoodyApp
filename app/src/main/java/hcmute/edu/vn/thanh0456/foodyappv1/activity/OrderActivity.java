package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.text.ParseException;
import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodResPageAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.OrderAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.OrderDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.ProductDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class OrderActivity extends AppCompatActivity {
    TabLayout tabLayout;
    private RecyclerView recyclerViewOrderList;
    public OrderAdaptor adapter;
    private OrderDomain orderDomain;
    Session session;
    CustomerDomain customerDomain;
    ArrayList<OrderDomain> orders;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Preparing"));
        tabLayout.addTab(tabLayout.newTab().setText("Delivered"));
        session = new Session(getApplicationContext());
        customerDomain = session.getSession("obj_customer");
        setupTabLayout();
        handleBottomNavigation();
        String status = getIntent().getStringExtra("status");
        if(status != null) {
            try {
                createExampleList(status);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            tabLayout.getTabAt(Integer.parseInt(status)).select();
        }
        else {
            try {
                createExampleList("0");
                recyclerViewOrder();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }
    public void filter() {
        try {
            createExampleList("0");
            recyclerViewOrder();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        adapter.filterList(orders);
    }
    private void setupTabLayout() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                try {
                    createExampleList(String.valueOf(tab.getPosition()));
                    recyclerViewOrder();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void createExampleList(String status) throws ParseException {
        OrderDAO orderDAO = new OrderDAO(getApplicationContext());
        orders = orderDAO.getAllOrderBaseOnStatus(String.valueOf(customerDomain.getCustomer_id()), status);
    }
    private void recyclerViewOrder() throws ParseException {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderList = findViewById(R.id.recyclerViewOrder);
        recyclerViewOrderList.setLayoutManager(linearLayoutManager);


        adapter = new OrderAdaptor(this, orders);
        recyclerViewOrderList.setAdapter(adapter);
    }
    private void handleBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_bar);
        bottomNavigationView.setSelectedItemId(R.id.nav_order);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_order:
                    return true;
                case R.id.nav_favorite:
                    startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_search:
                    startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.nav_explore:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
