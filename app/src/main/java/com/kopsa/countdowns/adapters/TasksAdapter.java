package com.kopsa.countdowns.adapters;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kopsa.countdowns.R;
import com.kopsa.countdowns.models.Task;
import com.kopsa.countdowns.util.Utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TasksAdapter
        extends RecyclerView.Adapter<TasksAdapter.ViewHolder>
        implements SimpleItemTouchHelperCallback.ItemTouchHelperAdapter {

    private final ArrayList<Task> mTaskList;

    public TasksAdapter(ArrayList<Task> tasks) {
        mTaskList = tasks;
    }

    @Override
    public void onItemDismiss(int position) {
        mTaskList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card_view, parent, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mCardView.setText(mTaskList[position]);

        holder.mTask = mTaskList.get(position);

        TextView textViewDesc = holder.mCardView.findViewById(R.id.task_desc_text_view);
        textViewDesc.setText(mTaskList.get(position).getDescription());

        TextView textViewCountdown = holder.mCardView.findViewById(R.id.countdown_text_view);
        Calendar dueDate = mTaskList.get(position).getDueDate();
        String countdown = Utilities.getTimeRemaining(dueDate);
        textViewCountdown.setText(countdown);

        if (dueDate.before(Calendar.getInstance())) {
            textViewCountdown.setTextColor(Color.RED);
        } else {
            textViewCountdown.setTextColor(Color.BLACK);
        }

        TextView dayOfWeek, dayOfMonth, monthAbbrev;
        dayOfWeek = holder.mCardView.findViewById(R.id.day_of_week);
        dayOfMonth = holder.mCardView.findViewById(R.id.day_of_month);
        monthAbbrev = holder.mCardView.findViewById(R.id.month_abbrev);

        dayOfWeek.setText(dueDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
        dayOfMonth.setText(String.valueOf(dueDate.get(Calendar.DAY_OF_MONTH)));
        monthAbbrev.setText(dueDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final CardView mCardView;
        public Task mTask;

        public ViewHolder(View view) {
            super(view);
            mCardView = view.findViewById(R.id.card_view);
        }
    }
}

