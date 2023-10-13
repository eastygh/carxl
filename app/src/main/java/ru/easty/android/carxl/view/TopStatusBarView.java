package ru.easty.android.carxl.view;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import ru.easty.android.carxl.R;

public class TopStatusBarView extends OverlayView {

    public TopStatusBarView(Context context) {
        super(context);
    }

    @Override
    void initView(Context context) {

        params.type = TYPE_APPLICATION_OVERLAY;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        params.gravity = Gravity.BOTTOM | Gravity.START;
        params.x = 0;
        params.y = dm.heightPixels - 60;
        params.width = dm.widthPixels;
        params.height = 60;

        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.top_status_bar_view, null);

    }

}
