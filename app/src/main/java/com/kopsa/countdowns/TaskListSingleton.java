package com.kopsa.countdowns;

import java.util.ArrayList;

/**
 * Created by kkopsa on 6/7/16.
 */
public class TaskListSingleton {
    private static TaskListSingleton instance = null;
    private static ArrayList<Task> tasks;

    protected TaskListSingleton() {
        // Exists only to defeat instantiation.
        tasks  = new ArrayList<Task>();
    }
    public static TaskListSingleton getInstance() {
        if(instance == null) {
            instance = new TaskListSingleton();
        }
        return instance;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void addTask(Task task) {
        tasks.add(task);
    }
}
