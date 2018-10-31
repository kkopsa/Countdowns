package com.kopsa.countdowns.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kopsa.countdowns.R;
import com.kopsa.countdowns.adapters.SimpleItemTouchHelperCallback;
import com.kopsa.countdowns.adapters.TasksAdapter;
import com.kopsa.countdowns.models.Task;

import java.util.ArrayList;
import java.util.Calendar;

public class TasksActivity extends AppCompatActivity {
    public static final ArrayList<Task> mTasksList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private CountDownTimer mCountDownTimer;

    private void addTestTasks() {
        Calendar calendar = Calendar.getInstance();
        mTasksList.add(new Task("1", calendar));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        mTasksList.add(new Task("2", calendar));

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        mTasksList.add(new Task("3", calendar));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addTestTasks();
        createTasksRecyclerView();
        registerTouchCallback();
        createCountdownTimer();
        createFloatingActionButton();
    }

    private void createFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        assert (fab != null);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTask(view);
            }
        });
    }

    private void createTasksRecyclerView() {
        mRecyclerView = findViewById(R.id.tasks_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        assert (mRecyclerView != null);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        Log.d("BIRDDOG", String.valueOf(mTasksList.size()));
        mAdapter = new TasksAdapter(mTasksList);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void registerTouchCallback() {
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((SimpleItemTouchHelperCallback.ItemTouchHelperAdapter) mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void createCountdownTimer() {
        mCountDownTimer = new CountDownTimer(999999, 1000) {

            public void onTick(long millisUntilFinished) {
                int itemRangeStart = 0;
                mAdapter.notifyItemRangeChanged(itemRangeStart, mAdapter.getItemCount());
                mAdapter.notifyDataSetChanged();
            }

            public void onFinish() {
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Called when the user clicks the add button
     */
    public void createTask(View view) {
        Intent intent = new Intent(this, CreateTaskActivity.class);
        startActivity(intent);
    }
}
