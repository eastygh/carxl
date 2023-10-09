package ru.easty.android.carxl.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.easty.android.carxl.R;
import ru.easty.android.carxl.apploader.AppModel;
import ru.easty.android.carxl.apploader.OnAppClick;

public class AppListBottomAdapter extends RecyclerView.Adapter<AppModelView> {

    ArrayList<AppModel> appList;
    OnAppClick clickListener;

    public AppListBottomAdapter(ArrayList<AppModel> appList, OnAppClick listener) {
        super();
        this.appList = appList;
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public AppModelView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView
                = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.app_bottom_list_item,
                        parent,
                        false);
        return new AppModelView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppModelView holder, int position) {
        Drawable icon = appList.get(position).getIcon();
        if (icon != null) {
            holder.mIcon.setImageDrawable(icon);
        }
        holder.bind(appList.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return appList == null ? 0 : appList.size();
    }

}
