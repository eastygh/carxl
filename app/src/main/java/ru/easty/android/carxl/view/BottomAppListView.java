package ru.easty.android.carxl.view;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Method;
import java.util.ArrayList;

import ru.easty.android.carxl.R;
import ru.easty.android.carxl.adapter.AppListBottomAdapter;
import ru.easty.android.carxl.apploader.AppModel;
import ru.easty.android.carxl.apploader.AppsLoader;
import ru.easty.android.carxl.util.U;

public class BottomAppListView extends OverlayView implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>> {

    private RecyclerView appListView;

    public BottomAppListView(Context context) {
        super(context);
        LoaderManager.getInstance(U.getLifecycleOwner(context)).initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, @Nullable Bundle args) {
        return new AppsLoader(U.getLifecycleOwner(context));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> data) {
        AppListBottomAdapter adapter = new AppListBottomAdapter(data, this::startApp);
        appListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<AppModel>> loader) {

    }

    void initView(Context context) {

        params.type = TYPE_APPLICATION_OVERLAY;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
        params.gravity = Gravity.BOTTOM | Gravity.START;
        params.x = 0;
        params.y = 0;
        params.width = dm.widthPixels;
        params.height = 60;

        layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.bottom_app_list_view, null);
        appListView = layout.findViewById(R.id.bottom_app_list_recycler_view);
        appListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

    }

    private void startApp(AppModel appModel) {
        // From android.app.WindowConfiguration
        final int WINDOWING_MODE_FREEFORM = 5;
        // Define the bounds in which the Activity will be launched into.
        Rect bounds = new Rect(500, 300, 700, 700);

        // Set the bounds as an activity option.
        ActivityOptions options = ActivityOptions.makeBasic();
        options.setLaunchBounds(bounds);
        Activity activity = U.getLifecycleOwner(context);
        int i = 0;
        try {
            Method method = ActivityOptions.class.getMethod("setLaunchWindowingMode", int.class);
            method.invoke(options, WINDOWING_MODE_FREEFORM);
        } catch (Exception e) {
            i++;
        }
        if (activity != null) {
            Intent intent = activity.getPackageManager().getLaunchIntentForPackage(appModel.getApplicationPackageName());
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT | Intent.FLAG_ACTIVITY_NEW_TASK);
                //intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                activity.startActivity(intent, options.toBundle());
            }
        }
    }

}
