package ru.easty.android.carxl.util;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import ru.easty.android.carxl.R;

public class U {

    public static AlertDialog showPermissionDialog(Context context, Callbacks callbacks) {
        AlertDialog.Builder builder;

        builder = buildPermissionDialogStandard(context, callbacks);

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);

        return dialog;
    }

    private static AlertDialog.Builder buildPermissionDialogStandard(Context context, Callbacks callbacks) {
        String message = context.getString(R.string.tb_permission_dialog_message, getAppName(context))
                + context.getString(R.string.tb_permission_dialog_instructions_phone);

        return new AlertDialog.Builder(context)
                .setTitle(R.string.tb_permission_dialog_title)
                .setMessage(message)
                .setPositiveButton(R.string.tb_action_grant_permission, (dialog, which) -> {
                    try {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + context.getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                        callbacks.onFinish.run();
                    } catch (ActivityNotFoundException e) {
                        callbacks.onError.run();
                    }
                });
    }

    public static CharSequence getAppName(Context context) {
        return context.getApplicationInfo().loadLabel(context.getPackageManager());
    }

    public static boolean canDrawOverlays(Context context) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(context);
    }

}
