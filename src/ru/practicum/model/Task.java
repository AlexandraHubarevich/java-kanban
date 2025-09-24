package ru.practicum.model;

import java.util.Objects;

public class Task implements Cloneable {
    protected String name;//Название, кратко описывающее суть задачи (например, «Переезд»).
    protected String description; //Описание, в котором раскрываются детали.
    protected int id;//Уникальный идентификационный номер задачи, по которому её можно будет найти
    protected TaskStatus taskStatus;

    public Task(String name, String description, TaskStatus taskStatus) {
        this.description = description;
        this.name = name;
        this.taskStatus = taskStatus;
    }
    public Task clone() throws CloneNotSupportedException{
        return (Task) super.clone();
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task otherTask = (Task) obj;
        return Objects.equals(id, otherTask.id);
    }

    @Override
    public int hashCode() {
        int hash = 17;
        if (id != 0) {
            hash = id;
        }

        return hash;
    }

    @Override
    public String toString() {
        return "{Название задачи Task='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", Статус задачи =" + taskStatus + '\'' +
                ", ID=" + id +
                '}';
    }
}




