package hcmute.edu.vn.thanh0456.foodyappv1.Adaptor;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import hcmute.edu.vn.thanh0456.foodyappv1.Domain.CategoryDomain;
import hcmute.edu.vn.thanh0456.foodyappv1.R;
import hcmute.edu.vn.thanh0456.foodyappv1.activity.SearchActivity;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.ViewHolder> {
    ArrayList<CategoryDomain>categoryDomains;
    private Context mcontext;
    public CategoryAdaptor(Context context, ArrayList<CategoryDomain> categoryDomains) {
        this.mcontext = context;
        this.categoryDomains = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.categoryName.setText(categoryDomains.get(position).getTitle());
        String picUrl = categoryDomains.get(position).getPic();

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picUrl, "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryPic);
        holder.layoutItem.setOnClickListener(view -> {
            Intent intent = new Intent(mcontext, SearchActivity.class);
            intent.putExtra("s_query", categoryDomains.get(position).getTitle());
            mcontext.startActivity(intent);
        });
    }
    public void release() {
        mcontext = null;
    }
    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryPic;
        ConstraintLayout layoutItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            layoutItem = itemView.findViewById(R.id.layoutItem);
        }
    }

}
