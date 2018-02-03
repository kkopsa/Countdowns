package com.kopsa.countdowns;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TaskUnitTests {
    @Test
    public void Creation() {
        Task myTask = new Task("", null);
        myTask.getDescription();
    }

    @Test
    public void getDateString() {
        Calendar now = Calendar.getInstance();
        Task myTask = new Task("", now);
        assertEquals(Utilities.getCalendarAsString(myTask.getDueDate()),
                Utilities.getCalendarAsString(myTask.getDueDate()));
    }
}