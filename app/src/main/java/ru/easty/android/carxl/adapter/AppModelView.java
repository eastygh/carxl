package ru.easty.android.carxl.adapter;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.easty.android.carxl.R;
import ru.easty.android.carxl.apploader.AppModel;
import ru.easty.android.carxl.apploader.OnAppClick;

public class AppModelView extends RecyclerView.ViewHolder {

    ImageView mIcon;
    View appModelView;
    AppModel app;

    public AppModelView(@NonNull View itemView) {
        super(itemView);
        appModelView = itemView;
        mIcon = itemView.findViewById(R.id.app_bottom_list_item_icon);
    }

    public void bind(AppModel app, OnAppClick listener) {
        this.app = app;
        appModelView.setOnClickListener(v -> listener.onClick(app));
    }

}
