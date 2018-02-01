package com.example.hpnotebook.newstime;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by Hp Notebook on 01-02-2018.
 */

public class DatePickerFragment extends DialogFragment{

    DatePickerDialog.OnDateSetListener onDateSet;
    private int year, month, day;

    public DatePickerFragment(){
    }

    public void setArguments(Bundle arguments) {
        super.setArguments(arguments);
        year = arguments.getInt("year");
        month = arguments.getInt("month");
        day = arguments.getInt("day");
    }

    public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
        onDateSet = ondate;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), onDateSet, year, month, day);
    }
}
