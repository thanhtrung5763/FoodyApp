package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.MessageFormat;
import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.FoodCartAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.DiscountDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.OrderDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscountDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDetailDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class CartActivity extends AppCompatActivity {
    private FoodCartAdaptor adapter;
    private RecyclerView recyclerViewCartList;
    TextView subtotal;
    TextView total;
    TextView taxmoney;
    TextView delimoney;
    TextView discountmoney;
    TextView tvtotal;
    TextView tax;
    TextView deli;
    TextView discount;
    ImageView backbtn;
    Button ordernow_btn;
    private OrderDomain orderDomain;
    DiscountDomain discountDomain;
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);
        load();
        loadFee();
        handleBottomNavigation();
    }
    public void updateTotalAndFee(double totalFromAdapter) {
        subtotal.setText(String.format("$%.2f", totalFromAdapter));
        taxmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", totalFromAdapter * 0.05)));
        if(delimoney.getText().toString().length() > 2) {
            delimoney.setText(MessageFormat.format("{0}$", String.format("%.2f", totalFromAdapter * 0.03)));
        }
        if(discount.getVisibility() == View.INVISIBLE) {
            total.setText(String.format("$%.2f", totalFromAdapter + totalFromAdapter * 0.05 + totalFromAdapter * 0.03));
        }
        else {
            switch (discount.getText().toString().substring(10, 13)) {
                case "30%":
                    discountmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", totalFromAdapter * .3)));
                    break;
                case "10%":
                    discountmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", totalFromAdapter * .1)));
                    break;
            }
            Double dismon = Double.parseDouble(discountmoney.getText().toString().substring(0, discountmoney.getText().toString().length() - 1));
            total.setText(String.format("$%.2f", totalFromAdapter + totalFromAdapter * 0.05 + totalFromAdapter * 0.03 - dismon));
        }
    }
    public void load() {
        tvtotal = findViewById(R.id.tvtotal);
        subtotal = findViewById(R.id.subtotal);
        total = findViewById(R.id.total);
        taxmoney = findViewById(R.id.taxmoney);
        delimoney = findViewById(R.id.delimoney);
        discountmoney = findViewById(R.id.discountmoney);
        tax = findViewById(R.id.tax);
        deli = findViewById(R.id.deli);
        discount = findViewById(R.id.discount);
        backbtn = findViewById(R.id.backbtn);
        ordernow_btn = findViewById(R.id.ordernow_btn);
        backbtn.setOnClickListener(view -> {
            onBackPressed();
        });
        ordernow_btn.setOnClickListener(view -> {
            OrderDAO orderDAO = new OrderDAO(this);
            orderDAO.updateStatusOfOrder(String.valueOf(orderDomain.getOrder_id()), "checkout");
            Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
            intent.putExtra("status", "1");
            startActivity(intent);
        });
        recyclerViewCategory();

        DiscountDAO discountDAO = new DiscountDAO(this);
        discountDomain = discountDAO.getDiscountByResId(orderDomain.getRestaurant_id());
    }
    public void loadFee() {
        subtotal.setText(String.format("$%.2f", orderDomain.getTotal()));
        taxmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", orderDomain.getTotal() * 0.05)));
        deli.setText("+Delivery Fee");
        delimoney.setText(MessageFormat.format("{0}$", String.format("%.2f", orderDomain.getTotal() * 0.03)));
        if(discountDomain != null) {
            switch (discountDomain.getName()) {
                case "Free Delivery": {
                    delimoney.setText("0$");
                    deli.setText("+Delivery Fee(Free)");
                    discount.setVisibility(View.INVISIBLE);
                    discountmoney.setVisibility(View.INVISIBLE);
                    break;
                }
                case "30% OFF": {
                    discountmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", orderDomain.getTotal() * .3)));
                    discount.setText("-Discount(30%)");
                    break;
                }
                case "10% OFF": {
                    discountmoney.setText(MessageFormat.format("{0}$", String.format("%.2f", orderDomain.getTotal() * .1)));
                    discount.setText("-Discount(10%)");
                    break;
                }
                default:
                    break;
            }
            Double delimon = Double.parseDouble(delimoney.getText().toString().substring(0, delimoney.getText().toString().length() - 1));
            Double taxmon = Double.parseDouble(taxmoney.getText().toString().substring(0, taxmoney.getText().toString().length() - 1));
            Double dismon = Double.parseDouble(discountmoney.getText().toString().substring(0, discountmoney.getText().toString().length() - 1));
            if(discount.getVisibility() == View.INVISIBLE) {
                total.setText(String.format("$%.2f", orderDomain.getTotal() + delimon + taxmon));
            }
            else {
                total.setText(String.format("$%.2f", orderDomain.getTotal() + delimon + taxmon - dismon));
            }
        }
        else {

            discount.setVisibility(View.INVISIBLE);
            discountmoney.setVisibility(View.INVISIBLE);
            Double delimon = Double.parseDouble(delimoney.getText().toString().substring(0, delimoney.getText().toString().length() - 1));
            Double taxmon = Double.parseDouble(taxmoney.getText().toString().substring(0, taxmoney.getText().toString().length() - 1));
            total.setText(String.format("$%.2f", orderDomain.getTotal() + delimon + taxmon));
        }
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCartList = findViewById(R.id.recyclerViewCart);
        recyclerViewCartList.setLayoutManager(linearLayoutManager);

        Bundle bundle = getIntent().getExtras();

        orderDomain = (OrderDomain) bundle.get("obj_order");
        OrderDAO orderDAO = new OrderDAO(this);

        ArrayList<OrderDetailDomain> category = orderDAO.getOrderDetailByOrderId(orderDomain);

        adapter = new FoodCartAdaptor(this, category);
        recyclerViewCartList.setAdapter(adapter);
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
        if (adapter != null) {
            adapter.release();
        }
    }
}
