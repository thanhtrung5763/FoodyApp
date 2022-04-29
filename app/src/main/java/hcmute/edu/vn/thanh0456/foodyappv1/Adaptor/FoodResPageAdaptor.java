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

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.FoodResPageDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;

public class FoodResPageAdaptor extends RecyclerView.Adapter<FoodResPageAdaptor.ViewHolder> {
    ArrayList<FoodResPageDomain> foodResPageDomains;

    public FoodResPageAdaptor(ArrayList<FoodResPageDomain> foodResPageDomains) {
        this.foodResPageDomains = foodResPageDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food_restaurant_page, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(foodResPageDomains.get(position).getName());
        holder.price.setText(String.valueOf(foodResPageDomains.get(position).getPrice()));

        String picUrl = "burger_1";
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
    }

    @Override
    public int getItemCount() {
        return foodResPageDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pic;
        TextView name;
        TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.food_respage_pic);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
