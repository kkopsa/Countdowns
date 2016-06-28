package com.kopsa.countdowns;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CreateTaskActivity extends AppCompatActivity {

    private static final String TAG = CreateTaskActivity.class.getSimpleName();

    private static Calendar mTaskDate;
    private String mTaskDesc;

    private static Button dateButton;
    private static Button timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        mTaskDate = Calendar.getInstance();

        dateButton = (Button) findViewById(R.id.pick_date);
        timeButton = (Button) findViewById(R.id.pick_time);


        final Button saveButton = (Button) findViewById(R.id.add_task);
        final Button cancelButton = (Button) findViewById(R.id.cancel_button);

        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set task description
                EditText et = (EditText) findViewById(R.id.task_desc);
                assert et != null;
                mTaskDesc = et.getText().toString();

                Task task = new Task(mTaskDesc, mTaskDate);
                TaskListSingleton.getInstance();
                TaskListSingleton.addTask(task);

                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        assert cancelButton != null;
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @NonNull
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
            mTaskDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mTaskDate.set(Calendar.MINUTE, minute);
            String time;
            if (hourOfDay > 12) {
                time = String.valueOf(hourOfDay - 12);
            }
            else {
                time = String.valueOf(hourOfDay);
            }

            if (minute < 10) {
                time += ":0";
            }
            else {
                time += ":";
            }
            time += String.valueOf(minute);
            time += " " + mTaskDate.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.ENGLISH);
            timeButton.setText(time);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            mTaskDate.set(year, month, day);
            String date = mTaskDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
            date += " " + String.valueOf(day);
            date += ", " + String.valueOf(year);
            dateButton.setText(date);
        }
    }
}


