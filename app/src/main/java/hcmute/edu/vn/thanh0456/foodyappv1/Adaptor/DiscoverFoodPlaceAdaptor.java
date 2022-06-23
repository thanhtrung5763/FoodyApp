package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.RestaurantPageActivity;

public class DiscoverFoodPlaceAdaptor extends RecyclerView.Adapter<DiscoverFoodPlaceAdaptor.ViewHolder> {
    ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains;
    private Context mcontext;
    public DiscoverFoodPlaceAdaptor(Context context, ArrayList<DiscoverFoodPlaceDomain> discoverFoodPlaceDomains) {
        this.mcontext = context;
        this.discoverFoodPlaceDomains = discoverFoodPlaceDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_discover, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DiscoverFoodPlaceDomain discoverFoodPlaceDomain = discoverFoodPlaceDomains.get(position);
        holder.restaurantName.setText(discoverFoodPlaceDomain.getName());
        holder.address.setText(discoverFoodPlaceDomain.getAddress());
        holder.score.setText(String.valueOf(discoverFoodPlaceDomain.getScore()));
        String rating_str = "(" + discoverFoodPlaceDomain.getRating() + " ratings" + ")";
        holder.rating.setText(rating_str);

        holder.promote.setText(discoverFoodPlaceDomain.getPromotion());
        String picUrl = discoverFoodPlaceDomain.getImg();
        String promotion = discoverFoodPlaceDomain.getPromotion();

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
            default:
                holder.promote.setBackground(null);
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.discoverPic);

        holder.cardView.setOnClickListener(view -> {
            goToResPage(discoverFoodPlaceDomain);
        });
    }
    private void goToResPage(DiscoverFoodPlaceDomain discoverFoodPlaceDomain) {
        Intent intent = new Intent(mcontext, RestaurantPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_restaurant", discoverFoodPlaceDomain);
        intent.putExtras(bundle);
        mcontext.startActivity(intent);
    }
    public void release() {
        mcontext = null;
    }
    @Override
    public int getItemCount() {
        return discoverFoodPlaceDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView restaurantName;
        ImageView discoverPic;
        TextView address;
        TextView score;
        TextView rating;
        TextView promote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            restaurantName = itemView.findViewById(R.id.restaurant);
            discoverPic = itemView.findViewById(R.id.discoverPic);
            address = itemView.findViewById(R.id.address);
            score = itemView.findViewById(R.id.score);
            rating = itemView.findViewById(R.id.rating);
            promote = itemView.findViewById(R.id.promote);
        }
    }
}
