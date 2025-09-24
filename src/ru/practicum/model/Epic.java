package ru.practicum.model;

import java.util.ArrayList;

public class Epic extends Task{

    private final ArrayList<Integer> epicSubTasksID = new ArrayList<>();

    public ArrayList<Integer> getEpicSubTasksID() {
        return epicSubTasksID;
    }

    public void addSubTaskToEpicId(SubTask subTask) {
        epicSubTasksID.add(subTask.getId());
    }

    public void deleteEpicSubTask(int subTaskID) {
        epicSubTasksID.remove((Integer) subTaskID);
    }

    public void cleanEpicSubTask() {
        epicSubTasksID.clear();
    }

    public Epic(String name, String description, TaskStatus taskStatus) {
        super(name, description, taskStatus);
    }

    @Override
    public String toString() {
        return "{Название задачи Epic='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус задачи =" + taskStatus + '\'' +
                ", ID=" + id + '\'' +
                ", epicSubTasksID=" + epicSubTasksID +
                '}';
    }
}

