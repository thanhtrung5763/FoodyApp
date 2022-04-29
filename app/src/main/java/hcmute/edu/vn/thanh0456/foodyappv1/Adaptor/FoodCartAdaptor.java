package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FoodCartDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class FoodCartAdaptor extends RecyclerView.Adapter<FoodCartAdaptor.ViewHolder> {
    ArrayList<FoodCartDomain> foodCartDomains;

    public FoodCartAdaptor(ArrayList<FoodCartDomain> foodCartDomains) {
        this.foodCartDomains = foodCartDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(foodCartDomains.get(position).getName());
        holder.size.setText(foodCartDomains.get(position).getSize());
        holder.price.setText(String.valueOf(foodCartDomains.get(position).getPrice()));
        holder.qty.setText(String.valueOf(foodCartDomains.get(position).getQty()));

        String picUrl = "kisspng_hamburg_";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return foodCartDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView name;
        TextView size;
        TextView price;
        TextView qty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.imgProduct);
            name = itemView.findViewById(R.id.name);
            size = itemView.findViewById(R.id.size);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);

        }
    }
}
