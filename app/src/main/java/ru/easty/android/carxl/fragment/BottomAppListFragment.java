package ru.easty.android.carxl.fragment;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import ru.easty.android.carxl.apploader2.AppListAdapter;

public class BottomAppListFragment extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>> {

    private AppListAdapter mAdapter;
    private RecyclerView appListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.bottom_app_list_fragment, container, false);
        appListView = fragment.findViewById(R.id.bottom_app_list_recycler_view);
        appListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoaderManager.getInstance(this).initLoader(0, null, this);
    }


    @NonNull
    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, Bundle bundle) {
        return new AppsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> apps) {
        AppListBottomAdapter adapter = new AppListBottomAdapter
                (apps, this::startApp);
        appListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<AppModel>> loader) {

    }

    private void startApp(AppModel appModel) {
        // From android.app.WindowConfiguration
        final int WINDOWING_MODE_FREEFORM = 5;
        // Define the bounds in which the Activity will be launched into.
        Rect bounds = new Rect(500, 300, 700, 700);

        // Set the bounds as an activity option.
        ActivityOptions options = ActivityOptions.makeBasic();
        options.setLaunchBounds(bounds);
        Activity activity = getActivity();
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
                startActivity(intent, options.toBundle());
            }
        }
    }

}
