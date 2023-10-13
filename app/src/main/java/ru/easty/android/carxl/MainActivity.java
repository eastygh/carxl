package ru.easty.android.carxl;

import static android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
import static ru.easty.android.carxl.util.U.canDrawOverlays;
import static ru.easty.android.carxl.util.U.showPermissionDialog;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.ComponentActivity;

import ru.easty.android.carxl.util.Callbacks;
import ru.easty.android.carxl.view.BottomAppListView;
import ru.easty.android.carxl.view.TopStatusBarView;

public class MainActivity extends ComponentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_screen_layout);
        createView(this);

//        getSupportFragmentManager().beginTransaction()
//                .add(layout.findViewById(R.id.bottom_view_fragment), new BottomAppListFragment())
//                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!canDrawOverlays(this)) {
            showPermissionDialog(this, new Callbacks());
        }
        //showPermissionDialog(getApplicationContext(), new Callbacks());
        //
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        //ignore
    }

    private void createView(Context context) {

        BottomAppListView controller = new BottomAppListView(context);
        TopStatusBarView statusBarView = new TopStatusBarView(context);

    }

    public Boolean checkFreeform() {
        //adb shell settings put global enable_freeform_support 1
        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_FREEFORM_WINDOW_MANAGEMENT);
    }


}