package ru.job4j.search;

import java.util.LinkedList;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com)
 * @version $Id$
 * @since 0.1
 */


public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();


    public void put(Task task) {
        if (this.tasks.isEmpty()) {
            this.tasks.add(task);
        }
        for (int index = 0; index < this.tasks.size(); index++) {
            if (task.getPriority()  <= this.tasks.get(index).getPriority()) {
                this.tasks.add(index, task);
                break;
            }
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
