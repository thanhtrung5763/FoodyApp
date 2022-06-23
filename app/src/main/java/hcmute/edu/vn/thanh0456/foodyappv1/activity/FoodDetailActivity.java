package hcmute.edu.vn.thanh0456.foodyappv1.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import hcmute.edu.vn.thanh0456.foodyappv1.Adaptor.CuisineAdaptor;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.FavoriteDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.OrderDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class FoodDetailActivity extends AppCompatActivity {
    AppCompatButton bS, bM, bL;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCuisineList;


    ProductDomain productDomain;
    ImageView prod_pic;
    TextView name;
    TextView time;
    TextView desc;
    TextView price;
    TextView addtocartbtn;
    ImageButton minus;
    ImageButton plus;
    TextView qty;
    ImageButton fav_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_details);

        prod_pic = findViewById(R.id.prod_pic);
        name = findViewById(R.id.name);
        time = findViewById(R.id.serve_time);
        desc = findViewById(R.id.description);
        price = findViewById(R.id.totalPrice);
        addtocartbtn = findViewById(R.id.addtocartbtn);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
        qty = findViewById(R.id.qty);
        fav_btn = findViewById(R.id.favorite_btn);
        loadFoodDetail();
        handleBottomNavigation();
        handleAnimationButton();
        if (productDomain.getIs_favorited()) {
            fav_btn.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            fav_btn.setImageResource(R.drawable.ic_baseline_favorited_48);
        }
        minus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(qty.getText().toString());
            if(quantity == 1) {
                return;
            }
            double totalPrice = Double.valueOf(price.getText().toString());

            double priceOnOne = totalPrice / quantity;
            quantity -= 1;
            qty.setText(String.valueOf(quantity));
            price.setText(String.format("%.2f", priceOnOne * quantity));
        });
        plus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(qty.getText().toString());
            double totalPrice = Double.valueOf(price.getText().toString());

            double priceOnOne = totalPrice / quantity;
            quantity += 1;
            qty.setText(String.valueOf(quantity));
            price.setText(String.format("%.2f", priceOnOne * quantity));
        });
        fav_btn.setOnClickListener(view -> {
            FavoriteDAO favoriteDAO = new FavoriteDAO(this);
            Session session = new Session(this);
            CustomerDomain customerDomain = session.getSession("obj_customer");
            Drawable drawable = fav_btn.getBackground();
            Drawable drawable1 = getApplicationContext().getDrawable(R.drawable.circle_white_bg);
            if (CompareDrawable.areDrawablesIdentical(drawable, drawable1)) {
                fav_btn.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_bg));
                fav_btn.setImageResource(R.drawable.ic_baseline_favorited_48);

                favoriteDAO.insert(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(productDomain.getProduct_id()));
                productDomain.setIs_favorited(!productDomain.getIs_favorited());

            }
            else {
                fav_btn.setBackground(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
                fav_btn.setImageResource(R.drawable.ic_baseline_favorite_48);

                favoriteDAO.delete(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(productDomain.getProduct_id()));
                productDomain.setIs_favorited(!productDomain.getIs_favorited());

            }
        });
        addtocartbtn.setOnClickListener(view -> {
            productDomain.setQty(Integer.parseInt(qty.getText().toString()));
            productDomain.setTotal(Double.valueOf(price.getText().toString()));
            productDomain.setPrice(productDomain.getTotal() / productDomain.getQty());
            Session session = new Session(getApplicationContext());
            CustomerDomain customerDomain = session.getSession("obj_customer");
            Date date = new Date(System.currentTimeMillis());
            OrderDomain orderDomain = new OrderDomain(productDomain.getRes_id(), customerDomain.getCustomer_id(), date, productDomain.getTotal(), 0);
            OrderDAO orderDAO = new OrderDAO(this);

            try {
                OrderDomain orderDomainPending = orderDAO.getOrderPending(String.valueOf(orderDomain.getRestaurant_id()), String.valueOf(customerDomain.getCustomer_id()));
                Bundle bundle = new Bundle();
                // if cart of res already exist
                if (orderDomainPending != null) {
                    orderDAO.updateOrderPending(orderDomainPending, productDomain);
                    bundle.putSerializable("obj_order", orderDomainPending);
                }
                else {
                    orderDomain.setOrder_id(orderDAO.makeOrder(orderDomain, productDomain));
                    orderDAO.makeOrderDetail(orderDomain, productDomain);
                    bundle.putSerializable("obj_order", orderDomain);
                }
                Intent intent = new Intent(this, CartActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
//        recyclerViewCuisine();
    }
    private void loadFoodDetail() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
//        Session session = new Session(getApplicationContext());
//        CustomerDomain customerDomain = session.getSession("obj_customer");
        productDomain = (ProductDomain) bundle.get("obj_product");
        int resId = getApplicationContext().getResources()
                .getIdentifier(productDomain.getImg(), "drawable", getApplicationContext().getPackageName());
        prod_pic.setImageResource(resId);
        name.setText(productDomain.getName());
        time.setText(MessageFormat.format("{0} mins", productDomain.getTime()));
        desc.setText(productDomain.getDesc());
        price.setText(String.valueOf(productDomain.getPrice()));
    }
    private void handleAnimationButton() {
        bS = findViewById(R.id.sizeS);
        bM = findViewById(R.id.sizeM);
        bL = findViewById(R.id.sizeL);
        double originPrice = productDomain.getPrice();
        bS.setOnClickListener(view -> {
            bS.setTextColor(Color.WHITE);
            bM.setTextColor(Color.BLACK);
            bM.setTextColor(Color.BLACK);
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            productDomain.setSize("s");
            double newPrice = originPrice * .9;
            price.setText(String.format("%.2f", newPrice * Integer.parseInt(qty.getText().toString())));
        });
        bM.setOnClickListener(view -> {
            bM.setTextColor(Color.WHITE);
            bS.setTextColor(Color.BLACK);
            bL.setTextColor(Color.BLACK);
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            productDomain.setSize("m");
            price.setText(String.format("%.2f", originPrice * Integer.parseInt(qty.getText().toString())));
        });
        bL.setOnClickListener(view -> {
            bL.setTextColor(Color.WHITE);
            bS.setTextColor(Color.BLACK);
            bM.setTextColor(Color.BLACK);
            bS.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bM.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_white_bg));
            bL.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.circle_bg));
            productDomain.setSize("l");
            double newPrice = originPrice * 1.1;
            price.setText(String.format("%.2f", newPrice * Integer.parseInt(qty.getText().toString())));
        });
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
}
