package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.MessageFormat;
import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.DAO.OrderDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.OrderDetailDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.CartActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.OrderActivity;

public class FoodCartAdaptor extends RecyclerView.Adapter<FoodCartAdaptor.ViewHolder> {
    ArrayList<OrderDetailDomain> foodCartDomains;
    private Context mcontext;
    public FoodCartAdaptor(Context context, ArrayList<OrderDetailDomain> foodCartDomains) {
        this.foodCartDomains = foodCartDomains;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetailDomain foodCartDomain = foodCartDomains.get(position);
        holder.name.setText(foodCartDomain.getName());
        holder.size.setText(MessageFormat.format("Size: {0}", foodCartDomain.getSize()));
        holder.price.setText(MessageFormat.format("${0}", String.format("%.2f", foodCartDomain.getSub_total())));
        holder.qty.setText(String.valueOf(foodCartDomain.getQty()));

        String picUrl = foodCartDomain.getProd_pic();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.minus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(holder.qty.getText().toString());
            if(quantity == 1) {
                return;
            }
            double priceOnOne = foodCartDomain.getPrice();;
            quantity -= 1;
            holder.qty.setText(String.valueOf(quantity));
            holder.price.setText(MessageFormat.format("${0}", String.format("%.2f", priceOnOne * quantity)));
            OrderDAO orderDAO = new OrderDAO(holder.itemView.getContext());
            double total = orderDAO.updateOrderPendingCart(foodCartDomain, quantity, -priceOnOne);
            foodCartDomain.setSub_total(foodCartDomain.getSub_total() - priceOnOne);
            if(mcontext instanceof CartActivity) {
                ((CartActivity)mcontext).updateTotalAndFee(total);
            }
        });
        holder.plus.setOnClickListener(view -> {
            int quantity = Integer.parseInt(holder.qty.getText().toString());

            double priceOnOne = foodCartDomain.getPrice();;
            quantity += 1;
            holder.qty.setText(String.valueOf(quantity));
            holder.price.setText(MessageFormat.format("${0}", String.format("%.2f", priceOnOne * quantity)));
            OrderDAO orderDAO = new OrderDAO(holder.itemView.getContext());
            double total = orderDAO.updateOrderPendingCart(foodCartDomain, quantity, priceOnOne);
            foodCartDomain.setSub_total(foodCartDomain.getSub_total() + priceOnOne);
            if(mcontext instanceof CartActivity) {
                ((CartActivity)mcontext).updateTotalAndFee(total);
            }
        });
        holder.remove.setOnClickListener(view -> {

            removeProduct(foodCartDomain);
            notifyDataSetChanged();
        });
    }
    public void removeProduct(OrderDetailDomain foodCartDomain) {
        foodCartDomains.remove(foodCartDomain);
        OrderDAO orderDAO = new OrderDAO(mcontext);
        if(foodCartDomains.size() == 0) {
            orderDAO.removeOrderDetailById(foodCartDomain.getOrder_detail_id());
            orderDAO.removeOrder(foodCartDomain.getOrder_id());
            if(mcontext instanceof CartActivity) {
                ((CartActivity)mcontext).onBackPressed();
            }
        }
        else {
            orderDAO.removeOrderDetailById(foodCartDomain.getOrder_detail_id());
            double total = orderDAO.updateOrderTotalAfterRemove(foodCartDomain.getOrder_id(), foodCartDomain.getSub_total());
            if(mcontext instanceof CartActivity) {
                ((CartActivity)mcontext).updateTotalAndFee(total);
            }
        }
    }
    @Override
    public int getItemCount() {
        return foodCartDomains.size();
    }
    public void release() {
        mcontext = null;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView name;
        TextView size;
        TextView price;
        TextView qty;
        ImageButton minus;
        ImageButton plus;
        ImageButton remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.imgProduct);
            name = itemView.findViewById(R.id.name);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);
            remove = itemView.findViewById(R.id.remove_button);
        }
    }
}
