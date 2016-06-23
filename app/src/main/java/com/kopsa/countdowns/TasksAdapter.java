package com.kopsa.countdowns;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private ArrayList<Task> mTaskList;

    @Override
    public void onItemDismiss(int position) {
        mTaskList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView mCardView;
        public ViewHolder(CardView v) {
            super(v);
            mCardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TasksAdapter(ArrayList<Task> myDataset) {
        mTaskList = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TasksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder((CardView) v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        //holder.mCardView.setText(mTaskList[position]);
        TextView textViewDesc = (TextView) holder.mCardView.findViewById(R.id.task_desc_text_view);
        textViewDesc.setText(mTaskList.get(position).getDesc());

        TextView textViewCountdown = (TextView) holder.mCardView.findViewById(R.id.countdown_text_view);
        Calendar dueDate = mTaskList.get(position).getDate();
        String countdown = getTimeRemaining(dueDate);
        textViewCountdown.setText(countdown);

        TextView dayOfWeek, dayOfMonth, monthAbbrev;
        dayOfWeek = (TextView) holder.mCardView.findViewById(R.id.day_of_week);
        dayOfMonth = (TextView) holder.mCardView.findViewById(R.id.day_of_month);
        monthAbbrev = (TextView) holder.mCardView.findViewById(R.id.month_abbrev);

        dayOfWeek.setText(dueDate.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
        dayOfMonth.setText(String.valueOf(dueDate.get(Calendar.DAY_OF_MONTH)));
        monthAbbrev.setText(dueDate.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));

    }

    private String getTimeRemaining(Calendar dueDate) {
        Calendar rightNow = Calendar.getInstance();
        long timeRemaining = dueDate.getTimeInMillis() - rightNow.getTimeInMillis();
        long days = timeRemaining / (1000*60*60*24);
        timeRemaining -= 1000*60*60*24*days;
        long hours = timeRemaining / (1000*60*60);
        timeRemaining -= 1000*60*60*hours;
        long minutes = timeRemaining / (1000*60);
        timeRemaining -= 1000*60*minutes;
        long seconds = timeRemaining / 1000;
        return String.valueOf(days) + " days " + String.valueOf(hours) + " hours "
                + String.valueOf(minutes) + " minutes " + String.valueOf(seconds) + " seconds";
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTaskList.size();
    }
}

