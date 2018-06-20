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
        if (this.tasks.isEmpty() || this.tasks.size() == 1) {
            this.tasks.add(task);
        } else if (task.getPriority() <= this.tasks.getFirst().getPriority()) {
            this.tasks.add(0, task);
        } else if (task.getPriority() >= this.tasks.getLast().getPriority()) {
            this.tasks.add(this.tasks.size() - 1, task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}
