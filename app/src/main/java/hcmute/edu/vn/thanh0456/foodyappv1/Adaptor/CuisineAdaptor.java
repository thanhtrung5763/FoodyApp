package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CuisineDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.MainActivity;

public class CuisineAdaptor extends RecyclerView.Adapter<CuisineAdaptor.ViewHolder> {
    ArrayList<CuisineDomain> cuisineDomains;
    int row_index = -1;
    private Context mcontext;
    public CuisineAdaptor(ArrayList<CuisineDomain> cuisineDomains) {
        this.cuisineDomains = cuisineDomains;
    }

    public CuisineAdaptor(ArrayList<CuisineDomain> cuisineDomains, Context context) {
        this.cuisineDomains = cuisineDomains;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cuisines, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CuisineDomain cuisineDomain = cuisineDomains.get(position);

        holder.name.setText(cuisineDomain.getName());

        for (CuisineDomain cuisineDomainItem : cuisineDomains) {
            if (cuisineDomainItem.isSelected() && cuisineDomainItem.getName().equals(cuisineDomain.getName())) {
                holder.name.setTextColor(Color.parseColor("#ffde0a"));
                holder.name.setBackgroundDrawable(ContextCompat.getDrawable(mcontext, R.drawable.search_bg_orange));
            }
        }
//        if(row_index==position) {
//            holder.name.setTextColor(Color.parseColor("#ffde0a"));
//            holder.name.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.search_bg_orange));
//        }
//        else {
//            holder.name.setTextColor(Color.parseColor("#808080"));
//            holder.name.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.search_bg));
//        }
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cuisineDomain.setSelected(!cuisineDomain.isSelected());
                view.setBackgroundDrawable(cuisineDomain.isSelected() ? ContextCompat.getDrawable(mcontext, R.drawable.search_bg_orange) : ContextCompat.getDrawable(mcontext, R.drawable.search_bg));
                holder.name.setTextColor(cuisineDomain.isSelected() ? Color.parseColor("#ffde0a") : Color.parseColor("#808080"));
                if(mcontext instanceof MainActivity) {
                    ((MainActivity) mcontext).cuisineDomains = cuisineDomains;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cuisineDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
