package ru.easty.android.carxl;

import static ru.easty.android.carxl.util.U.canDrawOverlays;
import static ru.easty.android.carxl.util.U.showPermissionDialog;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.FragmentActivity;

import ru.easty.android.carxl.util.Callbacks;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.home_screen_layout);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.bottom_view_fragment, new BottomAppListFragment())
//                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!canDrawOverlays(this)) {
            showPermissionDialog(this, new Callbacks());
        }
        createView(getApplicationContext());
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


//        BottomAppListView myView = new BottomAppListView(context);
//
//        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
//
//        /**
//         * Window type: phone.  These are non-application windows providing
//         * user interaction with the phone (in particular incoming calls).
//         * These windows are normally placed above all applications, but behind
//         * the status bar.
//         */
//        wmParams.type = getOverlayType();
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        wmParams.gravity = Gravity.START | Gravity.BOTTOM;
//        wmParams.x = 0;
//        wmParams.y = 0;
//        wmParams.width = 700;
//        wmParams.height = 700;
//        wm.addView(myView, wmParams);
    }

    public Boolean checkFreeform() {
        //adb shell settings put global enable_freeform_support 1
        return getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_FREEFORM_WINDOW_MANAGEMENT);
    }

    public static int getOverlayType() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
                ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                : WindowManager.LayoutParams.TYPE_PHONE;
    }

}