package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CustomerDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.Domain.SearchFoodDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.CompareDrawable;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.FoodDetailActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.SearchActivity;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.Session;

public class SearchFoodAdaptor extends RecyclerView.Adapter<SearchFoodAdaptor.ViewHolder> {
    ArrayList<SearchFoodDomain> searchFoodDomains;
    private Context mcontext;
    public SearchFoodAdaptor(Context context, ArrayList<SearchFoodDomain> searchFoodDomains) {
        this.mcontext = context;
        this.searchFoodDomains = searchFoodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_search, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SearchFoodDomain searchFoodDomain = searchFoodDomains.get(position);
        holder.prod_name.setText(searchFoodDomain.getProd_name());
        holder.res_name.setText(searchFoodDomain.getRes_name());
        holder.price.setText(String.format("$%.2f", searchFoodDomain.getPrice()));
        String picUrl = searchFoodDomain.getImg();
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);
        for (SearchFoodDomain searchFoodDomainItem : searchFoodDomains) {
            if (searchFoodDomainItem.getIs_favorited() && searchFoodDomain.getProd_name().equals(searchFoodDomainItem.getProd_name())) {
                holder.fav_btn.setBackground(ContextCompat.getDrawable(mcontext, R.drawable.circle_bg));
                holder.fav_btn.setImageResource(R.drawable.ic_baseline_favorited_24);
            }
        }
        holder.layoutItem.setOnClickListener(view -> {
            goToFoodDetailPage(searchFoodDomain);
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

                favoriteDAO.insert(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(searchFoodDomain.getProduct_id()));
                searchFoodDomain.setIs_favorited(!searchFoodDomain.getIs_favorited());
                if(mcontext instanceof SearchActivity) {
                    ((SearchActivity)mcontext).menu = searchFoodDomains;
                }
            }
            else {
                holder.fav_btn.setBackground(ContextCompat.getDrawable(mcontext, R.drawable.circle_white_bg));
                holder.fav_btn.setImageResource(R.drawable.ic_baseline_favorite_24);

                favoriteDAO.delete(String.valueOf(customerDomain.getCustomer_id()), String.valueOf(searchFoodDomain.getProduct_id()));
                searchFoodDomain.setIs_favorited(!searchFoodDomain.getIs_favorited());
                if(mcontext instanceof SearchActivity) {
                    ((SearchActivity)mcontext).menu = searchFoodDomains;
                }
            }
        });
    }

    private void goToFoodDetailPage(SearchFoodDomain searchFoodDomain) {
        Intent intent = new Intent(mcontext, FoodDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_product", searchFoodDomain);
        intent.putExtras(bundle);
        mcontext.startActivity(intent);
    }
    public void filterList(ArrayList<SearchFoodDomain> filtermenu) {
        searchFoodDomains = filtermenu;
        notifyDataSetChanged();
    }
    public void release() {
        mcontext = null;
    }
    @Override
    public int getItemCount() {
        return searchFoodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layoutItem;
        ImageView pic;
        TextView prod_name;
        TextView res_name;
        TextView price;
        ImageButton fav_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            pic = itemView.findViewById(R.id.food_respage_pic);
            prod_name = itemView.findViewById(R.id.prod_name);
            res_name = itemView.findViewById(R.id.res_name);
            price = itemView.findViewById(R.id.price);
            fav_btn = itemView.findViewById(R.id.favorite_btn);
        }
    }
}
