package com.kanchan.foodmandu.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kanchan.foodmandu.R;
import com.kanchan.foodmandu.ui.home.HomeViewModel;

import java.util.List;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.CategoryHolder> {

    Context context;
    List<HomeViewModel> listAdapter;

    public categoryAdapter(Context context, List<HomeViewModel> listAdapter) {
        this.context = context;
        this.listAdapter = listAdapter;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_adapter,parent,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {

        HomeViewModel dataModel=listAdapter.get(position);
        holder.etCategories.setText(dataModel.getCategory());
        holder.img.setImageResource(dataModel.getImg());


    }

    @Override
    public int getItemCount() {
        return listAdapter.size();
    }


    public class CategoryHolder extends RecyclerView.ViewHolder{

        EditText etCategories;
        ImageView img;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            etCategories=itemView.findViewById(R.id.etcategory);
            img=itemView.findViewById(R.id.img);
        }
    }
}
