package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import hcmute.edu.vn.thanh0456.foodyappv1.DAO.FavoriteDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.DAO.OrderDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.CartActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.CompareDrawable;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.FoodDetailActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.Session;

public class OrderAdaptor extends RecyclerView.Adapter<OrderAdaptor.ViewHolder> {
    ArrayList<OrderDomain> orderDomains;
    private Context mcontext;
    public OrderAdaptor(Context context, ArrayList<OrderDomain> orderDomains) {
        this.mcontext = context;
        this.orderDomains = orderDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final OrderDomain orderDomain = orderDomains.get(position);
        holder.name.setText(orderDomain.getRestaurant_name());
        holder.address.setText(orderDomain.getRestaurant_address());
        holder.score.setText(String.valueOf(orderDomain.getScore()));
        holder.rating.setText(String.valueOf(orderDomain.getRating()));

        holder.totalPrice.setText(String.format("Total: $%.2f", orderDomain.getTotal()));
        String picUrl = orderDomain.getRestaurant_pic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.layoutItem.setOnClickListener(view -> {
            goToOrderDetailPage(orderDomain);
        });

    }
    private void goToOrderDetailPage(OrderDomain orderDomain) {

        Session session = new Session(mcontext);
        CustomerDomain customerDomain = session.getSession("obj_customer");
        OrderDAO orderDAO = new OrderDAO(mcontext);
        try {
            OrderDomain orderDomainPending = orderDAO.getOrderPending(String.valueOf(orderDomain.getRestaurant_id()), String.valueOf(customerDomain.getCustomer_id()));
            Bundle bundle = new Bundle();
            bundle.putSerializable("obj_order", orderDomainPending);

            Intent intent = new Intent(mcontext, CartActivity.class);
            intent.putExtras(bundle);
            mcontext.startActivity(intent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void filterList(ArrayList<OrderDomain> filterorder) {
        orderDomains = filterorder;
        notifyDataSetChanged();
    }
    public void release() {
        mcontext = null;
    }
    @Override
    public int getItemCount() {
        return orderDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutItem;
        ImageView pic;
        TextView name;
        TextView address;
        TextView score;
        TextView rating;
        TextView totalPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            pic = itemView.findViewById(R.id.res_pic);
            name = itemView.findViewById(R.id.res_name);
            address = itemView.findViewById(R.id.res_address);
            score = itemView.findViewById(R.id.score);
            rating = itemView.findViewById(R.id.rating);
            totalPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}
