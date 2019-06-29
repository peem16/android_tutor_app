package com.example.peem16.eakqlearning;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    public buydetail s ;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();

        final Calendar b = Calendar.getInstance();

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        c.add(Calendar.DATE,3);
        // Create a new instance of DatePickerDialog and return it

        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);

        //set min date (ex. current date and time) - pass your custom date and time in milliseconds
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());

        b.add(Calendar.MONTH, 3);
        long result = b.getTimeInMillis();
        dialog.getDatePicker().setMaxDate(result);

        //set max date
        //dialog.getDatePicker().setMaxDate(maxDate);

        return dialog;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user


        s = new buydetail();
        s.populateSetDate(year, month+1, day);



    }

}