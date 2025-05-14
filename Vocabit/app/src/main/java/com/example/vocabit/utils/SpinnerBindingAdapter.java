package com.example.vocabit.utils;

import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

public class SpinnerBindingAdapter {

    @BindingAdapter("selectedValue")
    public static void setSelectedValue(Spinner spinner, Object selectedValue) {
        if (selectedValue == null) return;

        for (int i = 0; i < spinner.getCount(); i++) {
            if (selectedValue.toString().equals(spinner.getItemAtPosition(i).toString())) {
                spinner.setSelection(i);
                break;
            }
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    public static Object getSelectedValue(Spinner spinner) {
        return spinner.getSelectedItem();
    }

    @BindingAdapter("selectedValueAttrChanged")
    public static void setListener(Spinner spinner, final InverseBindingListener listener) {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, android.view.View view, int position, long id) {
                listener.onChange();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }
}
