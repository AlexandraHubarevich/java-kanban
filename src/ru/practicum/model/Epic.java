package ru.practicum.model;

import java.util.ArrayList;

public class Epic extends Task {


    final private ArrayList<Integer> epicSubTasksID = new ArrayList<>();

    public ArrayList<Integer> getEpicSubTasksID(int id) {
        return epicSubTasksID;
    }

    public void addSubTaskToEpicId(SubTask subTask) {
        epicSubTasksID.add(subTask.getId());
    }

    public void deleteEpicSubTask(int subTaskID) {
        for (int i = 0; i < epicSubTasksID.size(); i++) {
            if (epicSubTasksID.get(i) == subTaskID)
                epicSubTasksID.remove(i);
        }
    }

    public void cleanEpicSubTask() {
        epicSubTasksID.clear();
    }

    public Epic(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }

    public Epic(String name, String description, TaskStatus taskStatus, int id) {
        super(name, description, taskStatus, id);
    }

    @Override
    public String toString() {
        return "{Название задачи Epic='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус задачи =" + taskStatus + '\'' +
                ", ID=" + id +
                '}';
    }
}
