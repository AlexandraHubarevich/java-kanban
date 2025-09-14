package ru.practicum.model;

public class SubTask extends Task {
    private final int epicId;

    public int getEpicId() {
        return epicId;
    }

    public SubTask(String name, String description, TaskStatus taskStatus, int epicId) {
        super(name, description, taskStatus);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "{Название задачи SubTask='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус задачи =" + taskStatus + '\'' +
                ", ID =" + id + '\'' +
                ", epicID=" + epicId +
                '}';
    }
}