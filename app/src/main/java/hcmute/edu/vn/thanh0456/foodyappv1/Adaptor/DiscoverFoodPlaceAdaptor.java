package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class DiscoverFoodPlaceAdaptor extends RecyclerView.Adapter<DiscoverFoodPlaceAdaptor.ViewHolder> {
    ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains;

    public DiscoverFoodPlaceAdaptor(ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains) {
        this.discoverFoodPlaceDomains = discoverFoodPlaceDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_discover, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.restaurantName.setText(discoverFoodPlaceDomains.get(position).getRestaurant());
        holder.address.setText(discoverFoodPlaceDomains.get(position).getAddress());
        holder.score.setText(String.valueOf(discoverFoodPlaceDomains.get(position).getScore()));
        String rating_str = "(" + discoverFoodPlaceDomains.get(position).getRating() + " ratings" + ")";
        holder.rating.setText(rating_str);

        holder.promote.setText(discoverFoodPlaceDomains.get(position).getPromotion());
        String picUrl = "";
        String promotion = discoverFoodPlaceDomains.get(position).getPromotion();
        switch (position) {
            case 0: {
                picUrl = "dis_1";
                break;
            }
            case 1: {
                picUrl = "dis_2";
                break;
            }
            // anh voi do phan giai 200 * 245
            case 2: {
                picUrl = "dis_1";
                break;
            }
            case 3: {
                picUrl = "dis_2";
                break;
            }
            case 4: {
                picUrl = "dis_1";
                break;
            }
        }

        switch (promotion) {
            case "Free Delivery": {
                holder.promote.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.free_deli_bg));
                break;
            }
            case "30% OFF": {
                holder.promote.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.sale_30_percent_bg));
                break;
            }
            case "10% OFF": {
                holder.promote.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.sale_10_percent_bg));
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.discoverPic);
    }

    @Override
    public int getItemCount() {
        return discoverFoodPlaceDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView restaurantName;
        ImageView discoverPic;
        TextView address;
        TextView score;
        TextView rating;
        TextView promote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurant);
            discoverPic = itemView.findViewById(R.id.discoverPic);
            address = itemView.findViewById(R.id.address);
            score = itemView.findViewById(R.id.score);
            rating = itemView.findViewById(R.id.rating);
            promote = itemView.findViewById(R.id.promote);
        }
    }

}
