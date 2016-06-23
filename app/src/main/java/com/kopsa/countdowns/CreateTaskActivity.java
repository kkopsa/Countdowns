package com.kopsa.countdowns;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateTaskActivity extends AppCompatActivity {

    private static final String TAG = CreateTaskActivity.class.getSimpleName();

    private Calendar mTaskDate;
    private String mTaskDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        mTaskDate = Calendar.getInstance();

        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Button saveButton = (Button) findViewById(R.id.add_task);

        assert saveButton != null;
        assert timePicker != null;
        assert datePicker != null;

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set datetime to user's choice
                mTaskDate.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(), timePicker.getCurrentMinute());

                // set task description
                EditText et = (EditText) findViewById(R.id.task_desc);
                mTaskDesc = et.getText().toString();

                Task task = new Task(mTaskDesc, mTaskDate);
                TaskListSingleton.getInstance().addTask(task);

                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
}


