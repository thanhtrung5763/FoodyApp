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

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.DAO.FavoriteDAO;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.DiscoverFoodPlaceDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.ProductDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.CompareDrawable;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.FoodDetailActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.RestaurantPageActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.SearchActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.Session;

public class FoodResPageAdaptor extends RecyclerView.Adapter<FoodResPageAdaptor.ViewHolder> {
    ArrayList<ProductDomain> productDomains;
    private Context mcontext;
    public FoodResPageAdaptor(Context context, ArrayList<ProductDomain> productDomains) {
        this.mcontext = context;
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_food_restaurant_page, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ProductDomain productDomain = productDomains.get(position);
        holder.name.setText(productDomain.getName());
        holder.price.setText(String.valueOf(productDomain.getPrice()));
        String picUrl = productDomain.getImg();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        for (ProductDomain productDomainItem : productDomains) {
            if (productDomainItem.getIs_favorited() && productDomain.getName().equals(productDomainItem.getName())) {
                holder.fav_btn.setBackground(ContextCompat.getDrawable(mcontext, R.drawable.circle_bg));
                holder.fav_btn.setImageResource(R.drawable.ic_baseline_favorited_24);
            }
        }
        holder.layoutItem.setOnClickListener(view -> {
            goToFoodDetailPage(productDomain);
        });
        holder.fav_btn.setOnClickListener(view -> {
            FavoriteDAO favoriteDAO = new FavoriteDAO(mcontext);
            Session session = new Session(mcontext);
            CustomerDomain customerDomain = session.getSession("obj_customer");
            Drawable drawable = holder.fav_btn.getBackground();
            Drawable drawable1 = mcontext.getDrawable(R.drawable.circle_white_bg);
            if(CompareDrawable.areDrawablesIdentical(drawable, drawable1)) {
                holder.fav_btn.setBackground(ContextCompat.getDrawable(mcontext, R.drawable.circle_bg));
                holder.fav_btn.setImageResource(R.drawable.ic_baseline_favorited_24);

                favoriteDAO.insert(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(productDomain.getProduct_id()));
                productDomain.setIs_favorited(!productDomain.getIs_favorited());
            }
            else {
                holder.fav_btn.setBackground(ContextCompat.getDrawable(mcontext, R.drawable.circle_white_bg));
                holder.fav_btn.setImageResource(R.drawable.ic_baseline_favorite_24);

                favoriteDAO.delete(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(productDomain.getProduct_id()));
                productDomain.setIs_favorited(!productDomain.getIs_favorited());
            }
        });
    }
    private void goToFoodDetailPage(ProductDomain productDomain) {
        Intent intent = new Intent(mcontext, FoodDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product", productDomain);
        intent.putExtras(bundle);
        mcontext.startActivity(intent);
    }
    public void release() {
        mcontext = null;
    }
    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutItem;
        ImageView pic;
        TextView name;
        TextView price;
        ImageButton fav_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            pic = itemView.findViewById(R.id.food_respage_pic);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            fav_btn = itemView.findViewById(R.id.favorite_btn);
        }
    }
}
