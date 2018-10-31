package com.kopsa.countdowns.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.kopsa.countdowns.R;
import com.kopsa.countdowns.models.Task;
import com.kopsa.countdowns.persistence.TasksContract;
import com.kopsa.countdowns.persistence.TasksDbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateTaskActivity extends AppCompatActivity
        implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private final String TAG = CreateTaskActivity.class.getSimpleName();

    private Calendar mTaskDate;
    private String mTaskDesc;

    private Button dateButton;
    private Button timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        mTaskDate = Calendar.getInstance();
        mTaskDate.set(Calendar.SECOND, 0);

        dateButton = findViewById(R.id.pick_date);
        timeButton = findViewById(R.id.pick_time);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.US);
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("h:mm a", Locale.US);

        dateButton.setText(simpleDateFormat.format(mTaskDate.getTime()));
        timeButton.setText(simpleTimeFormat.format(mTaskDate.getTime()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.add(Menu.NONE, 1000, Menu.NONE, "SAVE");
        menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == 1000) {
            // set task description
            EditText et = findViewById(R.id.task_desc);
            assert et != null;
            mTaskDesc = et.getText().toString();

            Task task = new Task(mTaskDesc, mTaskDate);
            TasksActivity.mTasksList.add(task);

            //TODO: find out if this is right place for db instantiation
            TasksDbHelper tasksDbHelper = new TasksDbHelper(getBaseContext());

            SQLiteDatabase db = tasksDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(TasksContract.Task.COLUMN_NAME_DESC, mTaskDesc);
            values.put(TasksContract.Task.COLUMN_NAME_DUE_DATE, mTaskDate.getTimeInMillis());

            long newRowId = db.insert(TasksContract.Task.TABLE_NAME, null, values);

            Log.d(TAG, String.valueOf(newRowId));

            setResult(Activity.RESULT_OK);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void setTimeButton(String time) {
        timeButton.setText(time);
    }

    public void setDateButton(String date) {
        dateButton.setText(date);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        mTaskDate.set(year, month, day);
        String date = mTaskDate.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        date += " " + String.valueOf(day);
        date += ", " + String.valueOf(year);
        setDateButton(date);

        //TODO: show time picker automatically after date set
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mTaskDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        mTaskDate.set(Calendar.MINUTE, minute);
        String time;
        if (hourOfDay > 12) {
            time = String.valueOf(hourOfDay - 12);
        } else if (hourOfDay == 0) {
            time = String.valueOf(hourOfDay + 12);
        } else {
            time = String.valueOf(hourOfDay);
        }

        if (minute < 10) {
            time += ":0";
        } else {
            time += ":";
        }
        time += String.valueOf(minute);
        time += " " + mTaskDate.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.ENGLISH);
        setTimeButton(time);
    }

    public static class TimePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), (TimePickerDialog.OnTimeSetListener) getActivity(), hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }


    }

    public static class DatePickerFragment extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }
    }
}


