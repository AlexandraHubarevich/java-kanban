package ru.practicum.manager;

import ru.practicum.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> list = new ArrayList<>();

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(list);
    }

    @Override
    public void add(Task task) throws CloneNotSupportedException {
        if (task == null) {
            return;
        }
        if (list.size() > 10) {
            list.removeFirst();
        }
        list.add(task.clone());
    }
}