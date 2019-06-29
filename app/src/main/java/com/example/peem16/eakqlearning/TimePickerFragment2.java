package com.example.peem16.eakqlearning;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public  class TimePickerFragment2 extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    public Coures_detail s ;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it


        return new TimePickerDialog(getActivity(), this, hour, minute,

                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user

        if(hourOfDay < 8){

            Toast.makeText(getActivity(), "กรุณาเลือกหลัง 08.00",
                    Toast.LENGTH_SHORT).show();

        }else if(hourOfDay > 19){
            Toast.makeText(getActivity(), "กรุณาเลือกก่อน 20.00",
                    Toast.LENGTH_SHORT).show();

        }else{

            s = new Coures_detail();
            s.populateSettime(hourOfDay,minute);
        }



    }
}