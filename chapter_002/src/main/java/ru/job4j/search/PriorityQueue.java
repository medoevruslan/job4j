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
        int size = this.tasks.size();
        if (!this.tasks.isEmpty()) {
            for (int index = 0; index < size; index++) {
                if (task.getPriority() <= this.tasks.get(index).getPriority()) {
                    this.tasks.add(index, task);
                    break;
                } else if (index == this.tasks.size() - 1) {
                    this.tasks.add(task);
                }
            }
        } else {
            this.tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}

