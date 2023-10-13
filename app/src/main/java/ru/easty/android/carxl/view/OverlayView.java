package ru.easty.android.carxl.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

public abstract class OverlayView {

    protected Context context;
    protected LinearLayout layout;
    protected WindowManager wm;
    protected DisplayMetrics dm;
    protected WindowManager.LayoutParams params;

    public OverlayView(Context context) {
        this.context = context;
        wm = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        dm = context.getResources().getDisplayMetrics();
        params = new WindowManager.LayoutParams();
        initView(context);
        addView();
    }

    public void addView() {
        wm.addView(layout, params);
    }

    abstract void initView(Context context);

}
