package com.kopsa.countdowns;

import com.kopsa.countdowns.models.Task;
import com.kopsa.countdowns.util.Utilities;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TaskUnitTests {
    @Test
    public void getDueDateString() {
        // Assumes now and new task are created simultaneously
        Calendar now = Calendar.getInstance();
        String taskName = "My New Task";
        Task myTask = new Task(taskName, now);
        assertEquals(Utilities.getCalendarAsString(now), myTask.getDueDateAsString());
    }

    @Test
    public void getTimeRemaining() {
        final long timeRemaining = 10000;
        Calendar now = Calendar.getInstance();
        now.setTimeInMillis(System.currentTimeMillis() + timeRemaining);
        String taskName = "My New Task";
        Task myTask = new Task(taskName, now);
        assertEquals(timeRemaining, myTask.getTimeRemaining());
    }

    @Test
    public void formatTimeRemaining() {
        Calendar now = Calendar.getInstance();
        String taskName = "My New Task";
        Task myTask = new Task(taskName, now);
//        assertEquals("", myTask.getFormattedTimeRemaining());
    }
}