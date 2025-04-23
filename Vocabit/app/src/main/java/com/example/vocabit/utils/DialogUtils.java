package com.example.vocabit.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vocabit.R;

public class DialogUtils {

    private DialogUtils(){
        //do not init
    }

    public static androidx.appcompat.app.AlertDialog dialogConfirm(Context context,
                                                                   String msg,
                                                                   String btnPositive,
                                                                   DialogInterface.OnClickListener positive,
                                                                   String btnNegative,
                                                                   DialogInterface.OnClickListener negative) {

        androidx.appcompat.app.AlertDialog dialog = new androidx.appcompat.app.AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton(btnPositive, positive)
                .setNegativeButton(btnNegative, negative)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
        TextView message = dialog.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9.10f);
        }
        Button buttonPositive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (buttonPositive != null) {
            buttonPositive.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7.80f);
        }

        Button buttonN = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if (buttonN != null) {
            buttonN.setTextSize(TypedValue.COMPLEX_UNIT_SP, 7.80f);
        }
        return dialog;
    }

    public static Dialog createDialogLoading(Context context, String msg) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

        View layout = inflater.inflate(R.layout.layout_progressbar, null);
        if(msg!=null) {
            TextView progressbarMsg = (TextView) layout.findViewById(R.id.progressbar_msg);
            progressbarMsg.setText(msg);
        }

        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(layout);
        return builder.create();
    }
}
