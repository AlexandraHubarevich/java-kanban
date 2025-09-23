package ru.practicum.manager;

import ru.practicum.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    List<Task> list=new ArrayList<>();
    @Override
   public List<Task> getHistory() {
        return list;
    }

    @Override
    public void add(Task task) {
        if(list.size() < 10) {
               list.add(task);
          } else {
                  list.remove(0);
                  list.add(task);
              }
           }
    }