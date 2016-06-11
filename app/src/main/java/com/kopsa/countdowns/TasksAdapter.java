package com.kopsa.countdowns;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {
    //private String[] mDataset;
    private ArrayList<Task> mDataset;

    private static final String TAG = TasksAdapter.class.getSimpleName();

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
        mDataset = myDataset;
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

        //holder.mCardView.setText(mDataset[position]);
        TextView textViewDesc = (TextView) holder.mCardView.findViewById(R.id.task_desc_text_view);
        textViewDesc.setText(mDataset.get(position).getmDesc());

        TextView textViewCountdown = (TextView) holder.mCardView.findViewById(R.id.countdown_text_view);
        Date dueDate = mDataset.get(position).getmDate();
        String countdown = getTimeRemaining(dueDate);
        textViewCountdown.setText(countdown);

        TextView dayOfWeek, dayOfMonth, monthAbbrev;
        dayOfWeek = (TextView) holder.mCardView.findViewById(R.id.day_of_week);
        dayOfMonth = (TextView) holder.mCardView.findViewById(R.id.day_of_month);
        monthAbbrev = (TextView) holder.mCardView.findViewById(R.id.month_abbrev);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dueDate.getTime());

        dayOfWeek.setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH));
        dayOfMonth.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        monthAbbrev.setText(cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));


    }

    private String getTimeRemaining(Date dueDate) {
        Date date = new Date();
        long timeRemaining = dueDate.getTime() - date.getTime();
        int days = (int) (timeRemaining / (1000*60*60*24));
        return String.valueOf(days) + " days";
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

